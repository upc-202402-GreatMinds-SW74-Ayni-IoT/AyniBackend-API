package com.greatminds.ayni.monitoring.interfaces.rest.transform;

import com.greatminds.ayni.monitoring.domain.model.commands.UpdateSensorCommand;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorResource;

public class UpdateSensorCommandFromResourceAssembler {
    public static UpdateSensorCommand toCommandFromResource(Long sensorId, UpdateSensorResource resource){
        return new UpdateSensorCommand(sensorId, resource.temperature(), resource.hydration(), resource.oxygenation(),
                resource.cropId());
    }
}
