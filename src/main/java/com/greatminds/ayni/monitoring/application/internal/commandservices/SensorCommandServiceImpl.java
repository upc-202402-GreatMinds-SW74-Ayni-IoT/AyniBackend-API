package com.greatminds.ayni.monitoring.application.internal.commandservices;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateSensorCommand;
import com.greatminds.ayni.monitoring.domain.services.SensorCommandService;
import com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories.SensorRepository;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorValuesResource;
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
        var sensor = new Sensor(command.temperature(), command.hydration(), command.oxygenation(), command.waterLevel(), command.cropId());
        sensorRepository.save(sensor);
        return sensor.getId();
    }

    @Override
    public Long deleteSensor(Long id) {
        if (!sensorRepository.existsById(id)) throw new IllegalArgumentException("Sensor does not exist");
        sensorRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateSensor(Long id, UpdateSensorResource request) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor does not exist"));

        sensor.update(new Sensor(request.temperature(), request.hydration(), request.oxygenation(), request.waterLevel(), request.cropId()));
        sensorRepository.save(sensor);
        return sensor.getId();
    }

    @Override
    public Long updateSensorValues(Long cropId, UpdateSensorValuesResource request) {
        Sensor sensor = sensorRepository.findSensorByCropId(cropId)
                .orElseThrow(() -> new IllegalArgumentException("Sensor does not exist"));

        sensor.updateValues(request.temperature(), request.hydration(), request.oxygenation(), request.waterLevel());
        sensorRepository.save(sensor);
        return sensor.getId();
    }
}
