package com.greatminds.ayni.product.interfaces.rest.resources;

public record ProductResource(Long id, String name, String description, String recommendedCultivationDistance, String recommendedCultivationDepth,
                              String recommendedGrowingClimate, String recommendedSoilType, String recommendedGrowingSeason, String imageUrl,
                              Long userId) {
}
