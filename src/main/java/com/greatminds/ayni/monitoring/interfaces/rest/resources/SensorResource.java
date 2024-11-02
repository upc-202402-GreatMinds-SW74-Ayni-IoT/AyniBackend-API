package com.greatminds.ayni.monitoring.interfaces.rest.resources;

public record SensorResource(Long id, Float temperature, Float hydration, Float oxygenation, Float waterLevel, Long cropId) {
}
