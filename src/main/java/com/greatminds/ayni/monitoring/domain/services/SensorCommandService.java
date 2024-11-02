package com.greatminds.ayni.monitoring.domain.services;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateSensorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.DeleteSensorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.UpdateSensorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.UpdateSensorValuesCommand;

import java.util.Optional;

public interface SensorCommandService {
    Long handle(CreateSensorCommand command);
    Optional<Sensor> handle(UpdateSensorCommand command);
    Optional<Sensor> handle(UpdateSensorValuesCommand command);
    void handle(DeleteSensorCommand command);
}
