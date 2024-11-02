package com.greatminds.ayni.planification.interfaces.rest.resources;

public record CropResource(Long id, String name, Boolean pickUpWeed, Boolean fertilizeCrop, Boolean oxygenateCrop, Boolean makeCropLine,
                           Boolean makeCropHole, Long wateringDays, Long pestCleanupDays,Float recommendedTemperature, Float recommendedHumidity, Float recommendedOxygen, Float recommendedWaterLevel, Long productId, Long userId) {
}
