package com.greatminds.ayni.planification.domain.services;

import com.greatminds.ayni.planification.domain.model.commands.CreateCropCommand;

/**
 * Service for handling crop commands.
 */
public interface CropCommandService {
    Long handle(CreateCropCommand command);
}
