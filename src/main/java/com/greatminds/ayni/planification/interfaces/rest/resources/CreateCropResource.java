package com.greatminds.ayni.planification.interfaces.rest.resources;

public record CreateCropResource(String name, Boolean pickUpWeed, Boolean fertilizeCrop, Boolean oxygenateCrop, Boolean makeCropLine,
                                 Boolean makeCropHole, Long wateringDays, Long pestCleanupDays, Long productId, Long userId) {
}
