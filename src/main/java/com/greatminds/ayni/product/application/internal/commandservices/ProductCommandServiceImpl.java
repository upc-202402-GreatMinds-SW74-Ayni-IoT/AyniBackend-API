package com.greatminds.ayni.product.application.internal.commandservices;

import com.greatminds.ayni.product.domain.model.commands.CreateProductCommand;
import com.greatminds.ayni.product.domain.model.entities.Product;
import com.greatminds.ayni.product.domain.services.ProductCommandService;
import com.greatminds.ayni.product.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * Implementacion of {@link ProductCommandService} that handles the creation of products.
 */
@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Long handle(CreateProductCommand command) {
        var product = new Product(command.name(), command.description(), command.recommendedCultivationDistance(),
                command.recommendedCultivationDepth(), command.recommendedGrowingClimate(), command.recommendedSoilType(),
                command.recommendedGrowingSeason(), command.imageUrl(), command.userId());
        productRepository.save(product);
        return product.getId();
    }
}
