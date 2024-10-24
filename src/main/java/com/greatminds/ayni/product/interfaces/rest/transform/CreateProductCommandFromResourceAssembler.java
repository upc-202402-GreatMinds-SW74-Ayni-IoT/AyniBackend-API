package com.greatminds.ayni.product.interfaces.rest.transform;

import com.greatminds.ayni.product.domain.model.commands.CreateProductCommand;
import com.greatminds.ayni.product.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(resource.name(), resource.description(), resource.recommendedCultivationDistance(),
                resource.recommendedCultivationDepth(), resource.recommendedGrowingClimate(), resource.recommendedSoilType(),
                resource.recommendedGrowingSeason(), resource.imageUrl(), resource.userId());
    }
}
