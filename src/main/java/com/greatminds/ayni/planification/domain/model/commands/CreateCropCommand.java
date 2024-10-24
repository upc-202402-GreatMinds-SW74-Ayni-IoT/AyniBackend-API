package com.greatminds.ayni.planification.domain.model.commands;

public record CreateCropCommand(String name, Boolean pickUpWeed, Boolean fertilizeCrop, Boolean oxygenateCrop, Boolean makeCropLine,
                                Boolean makeCropHole, Long wateringDays, Long pestCleanupDays, Long productId, Long userId) {
}