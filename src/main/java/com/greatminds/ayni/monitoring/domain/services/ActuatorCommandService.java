package com.greatminds.ayni.monitoring.domain.services;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import com.greatminds.ayni.monitoring.domain.model.commands.CreateActuatorCommand;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateActuatorResource;

import java.util.Optional;

public interface ActuatorCommandService {
    Long handle(CreateActuatorCommand command);
    Long deleteActuator(Long id);
    Long updateActuator(Long id, UpdateActuatorResource request);
}
