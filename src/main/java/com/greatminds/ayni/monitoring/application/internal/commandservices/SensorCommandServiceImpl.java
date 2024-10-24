package com.greatminds.ayni.monitoring.application.internal.commandservices;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateSensorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.DeleteSensorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.UpdateSensorCommand;
import com.greatminds.ayni.monitoring.domain.services.SensorCommandService;
import com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorCommandServiceImpl implements SensorCommandService {
    private final SensorRepository sensorRepository;

    public SensorCommandServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Long handle(CreateSensorCommand command) {
        var sensor = new Sensor(command.temperature(), command.hydration(), command.oxygenation(), command.cropId());
        sensorRepository.save(sensor);
        return sensor.getId();
    }

    @Override
    public void handle(DeleteSensorCommand command) {
        if (!sensorRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Sensor with ID " + command.id() + " not found");
        }
        sensorRepository.deleteById(command.id());
    }

    @Override
    public Optional<Sensor> handle(UpdateSensorCommand command) {
        if (!sensorRepository.existsById(command.id())) throw new IllegalArgumentException("Sensor does not exist");
        var sensorToUpdate = sensorRepository.findById(command.id()).get();
        var updatedSensor = sensorRepository.save(sensorToUpdate.update(command.temperature(), command.hydration(), command.oxygenation(), command.cropId()));
        return Optional.of(updatedSensor);
    }
}
