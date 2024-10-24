package com.greatminds.ayni.monitoring.application.internal.commandservices;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateActuatorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.DeleteActuatorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.UpdateActuatorCommand;
import com.greatminds.ayni.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.greatminds.ayni.monitoring.domain.services.ActuatorCommandService;
import com.greatminds.ayni.monitoring.domain.services.SensorQueryService;
import com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories.ActuatorRepository;
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
    public void handle(DeleteActuatorCommand command) {
        if (!actuatorRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Actuator with ID " + command.id() + " not found");
        }
        actuatorRepository.deleteById(command.id());
    }

    @Override
    public Optional<Actuator> handle(UpdateActuatorCommand command) {
        if (!actuatorRepository.existsById(command.id())) throw new IllegalArgumentException("Actuator does not exist");
        var actuatorToUpdate = actuatorRepository.findById(command.id()).get();
        var updatedActuator = actuatorRepository.save(actuatorToUpdate.updateStatus(command.status()));
        return Optional.of(updatedActuator);
    }
}
