package com.greatminds.ayni.monitoring.interfaces.rest.transform;

import com.greatminds.ayni.monitoring.domain.model.commands.UpdateActuatorCommand;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateActuatorResource;

public class UpdateActuatorCommandFromResourceAssembler {
    public static UpdateActuatorCommand toCommandFromResource(Long actuatorId, UpdateActuatorResource resource) {
        return new UpdateActuatorCommand(actuatorId, resource.status());
    }
}
