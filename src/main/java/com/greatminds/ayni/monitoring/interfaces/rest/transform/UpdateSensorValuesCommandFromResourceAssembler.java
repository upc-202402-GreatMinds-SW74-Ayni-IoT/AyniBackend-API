package com.greatminds.ayni.monitoring.interfaces.rest.transform;

import com.greatminds.ayni.monitoring.domain.model.commands.UpdateSensorValuesCommand;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorValuesResource;

public class UpdateSensorValuesCommandFromResourceAssembler {
    public static UpdateSensorValuesCommand toCommandFromResource(Long cropId, UpdateSensorValuesResource resource) {
        return new UpdateSensorValuesCommand(cropId, resource.temperature(), resource.hydration(), resource.oxygenation(), resource.waterLevel());
    }
}
