package com.greatminds.ayni.monitoring.domain.model.commands;

public record UpdateSensorCommand(Long id, Float temperature, Float hydration, Float oxygenation, Long cropId) {
}
