package com.greatminds.ayni.monitoring.application.internal.commandservices;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateActuatorCommand;
import com.greatminds.ayni.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.greatminds.ayni.monitoring.domain.services.ActuatorCommandService;
import com.greatminds.ayni.monitoring.domain.services.SensorQueryService;
import com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories.ActuatorRepository;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateActuatorResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActuatorCommandServiceImpl implements ActuatorCommandService {
    private final ActuatorRepository actuatorRepository;
    private final SensorQueryService sensorQueryService;

    public ActuatorCommandServiceImpl(ActuatorRepository actuatorRepository, SensorQueryService sensorQueryService) {
        this.actuatorRepository = actuatorRepository;
        this.sensorQueryService = sensorQueryService;

    }

    @Override
    public Long handle(CreateActuatorCommand command) {
        var getSensorByIdQuey = new GetSensorByIdQuery(command.sensorId());
        var sensor = sensorQueryService.handle(getSensorByIdQuey).orElseThrow();
        var actuator = new Actuator(command.status(), sensor);
        actuatorRepository.save(actuator);
        return actuator.getId();
    }

    @Override
    public Long deleteActuator(Long id){
        if (!actuatorRepository.existsById(id)) {
            throw new IllegalArgumentException("Actuator with id " + id + " does not exist");
        }
        actuatorRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateActuator(Long id, UpdateActuatorResource request){
        Actuator actuator = actuatorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actuator with id " + id + " does not exist"));

        actuator.updateStatus(request.status());
        actuatorRepository.save(actuator);
        return actuator.getId();
    }
}
