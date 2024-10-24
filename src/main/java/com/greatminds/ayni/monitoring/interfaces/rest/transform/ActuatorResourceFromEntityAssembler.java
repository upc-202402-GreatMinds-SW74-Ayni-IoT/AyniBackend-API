package com.greatminds.ayni.monitoring.interfaces.rest.transform;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.ActuatorResource;

public class ActuatorResourceFromEntityAssembler {
    public static ActuatorResource toResourceFromEntity(Actuator actuator) {
        return new ActuatorResource(actuator.getId(), actuator.getStatus(), actuator.getSensor().getId());
    }
}
