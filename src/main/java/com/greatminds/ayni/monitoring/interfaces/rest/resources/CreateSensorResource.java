package com.greatminds.ayni.monitoring.interfaces.rest.resources;

public record CreateSensorResource(Float temperature, Float hydration, Float oxygenation, Long cropId) {
}
