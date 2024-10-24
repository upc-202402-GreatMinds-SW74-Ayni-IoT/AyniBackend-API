package com.greatminds.ayni.monitoring.domain.services;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateActuatorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.DeleteActuatorCommand;
import com.greatminds.ayni.monitoring.domain.model.commands.UpdateActuatorCommand;

import java.util.Optional;

public interface ActuatorCommandService {
    Long handle(CreateActuatorCommand command);
    Optional<Actuator> handle(UpdateActuatorCommand command);
    void handle(DeleteActuatorCommand command);
}
