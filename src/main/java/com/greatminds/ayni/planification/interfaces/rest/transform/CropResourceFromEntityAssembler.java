package com.greatminds.ayni.planification.interfaces.rest.transform;

import com.greatminds.ayni.planification.domain.model.aggregates.Crop;
import com.greatminds.ayni.planification.interfaces.rest.resources.CropResource;

public class CropResourceFromEntityAssembler {
    public static CropResource toResourceFromEntity(Crop entity){
        return new CropResource(
                entity.getId(),
                entity.getName(),
                entity.getPickUpWeed(),
                entity.getFertilizeCrop(),
                entity.getOxygenateCrop(),
                entity.getMakeCropLine(),
                entity.getMakeCropHole(),
                entity.getWateringDays(),
                entity.getPestCleanupDays(),
                entity.getRecommendedTemperature(),
                entity.getRecommendedHumidity(),
                entity.getRecommendedOxygen(),
                entity.getRecommendedWaterLevel(),
                entity.getProduct().getId(),
                entity.getUserId()
        );
    }
}
