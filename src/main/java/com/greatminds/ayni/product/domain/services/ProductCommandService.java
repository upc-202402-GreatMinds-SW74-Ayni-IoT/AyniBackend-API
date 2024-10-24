package com.greatminds.ayni.product.domain.services;

import com.greatminds.ayni.product.domain.model.commands.CreateProductCommand;

/**
 * Service for handling product commands.
 */
public interface ProductCommandService {
    Long handle(CreateProductCommand command);
}
