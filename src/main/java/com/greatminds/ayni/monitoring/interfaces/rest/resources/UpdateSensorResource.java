package com.greatminds.ayni.monitoring.interfaces.rest.resources;

public record UpdateSensorResource(Float temperature, Float hydration, Float oxygenation, Long cropId) {
}
