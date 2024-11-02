package com.greatminds.ayni.planification.interfaces.rest.transform;

import com.greatminds.ayni.planification.domain.model.commands.CreateCropCommand;
import com.greatminds.ayni.planification.interfaces.rest.resources.CreateCropResource;

public class CreateCropCommandFromResourceAssembler {
    public static CreateCropCommand toCommandFromResource(CreateCropResource resource){
        return new CreateCropCommand(resource.name(),resource.pickUpWeed(),resource.fertilizeCrop(),resource.oxygenateCrop(),
                resource.makeCropLine(),resource.makeCropHole(),resource.wateringDays(),resource.pestCleanupDays(),
                resource.recommendedTemperature(), resource.recommendedHumidity(), resource.recommendedOxygen(),
                resource.recommendedWaterLevel(), resource.productId(), resource.userId());
    }
}
