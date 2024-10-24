package com.greatminds.ayni.monitoring.interfaces.rest.transform;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.SensorResource;

public class SensorResourceFromEntityAssembler {
    public static SensorResource toResourceFromEntity(Sensor sensor){
        return new SensorResource(sensor.getId(),sensor.getTemperature(),sensor.getHydration(),sensor.getOxygenation(),
                sensor.getCropId());
    }
}
