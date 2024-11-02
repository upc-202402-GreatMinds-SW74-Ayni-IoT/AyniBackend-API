package com.greatminds.ayni.monitoring.domain.services;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateSensorCommand;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorValuesResource;

import java.util.Optional;

public interface SensorCommandService {
    Long handle(CreateSensorCommand command);
    Long deleteSensor(Long id);
    Long updateSensor(Long id, UpdateSensorResource request);
    Long updateSensorValues(Long cropId, UpdateSensorValuesResource request);
}
