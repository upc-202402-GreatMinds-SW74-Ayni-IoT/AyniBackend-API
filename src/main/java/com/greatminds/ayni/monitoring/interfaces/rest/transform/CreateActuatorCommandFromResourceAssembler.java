package com.greatminds.ayni.monitoring.interfaces.rest.transform;

import com.greatminds.ayni.monitoring.domain.model.commands.CreateActuatorCommand;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.CreateActuatorResource;

public class CreateActuatorCommandFromResourceAssembler {
    public static CreateActuatorCommand toCommandFromResource(CreateActuatorResource resource) {
        return new CreateActuatorCommand(resource.status(), resource.sensorId());
    }
}
