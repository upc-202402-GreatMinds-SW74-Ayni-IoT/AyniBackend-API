package com.greatminds.ayni.monitoring.domain.model.commands;

public record UpdateSensorValuesCommand(Long cropId, Float temperature, Float hydration, Float oxygenation, Float waterLevel) {
}
