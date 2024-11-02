package com.greatminds.ayni.monitoring.interfaces.rest.resources;

public record UpdateSensorValuesResource(Float temperature, Float hydration, Float oxygenation, Float waterLevel) {
}
