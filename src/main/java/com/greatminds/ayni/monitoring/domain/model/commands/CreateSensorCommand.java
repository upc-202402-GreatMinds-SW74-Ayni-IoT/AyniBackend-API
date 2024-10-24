package com.greatminds.ayni.monitoring.domain.model.commands;

public record CreateSensorCommand(Float temperature, Float hydration, Float oxygenation, Long cropId) {
}
