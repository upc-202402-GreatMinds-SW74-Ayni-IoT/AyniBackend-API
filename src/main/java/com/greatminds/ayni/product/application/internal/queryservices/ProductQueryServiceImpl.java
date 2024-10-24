package com.greatminds.ayni.product.application.internal.queryservices;

import com.greatminds.ayni.product.domain.model.entities.Product;
import com.greatminds.ayni.product.domain.model.queries.GetAllProductsQuery;
import com.greatminds.ayni.product.domain.model.queries.GetProductByIdQuery;
import com.greatminds.ayni.product.domain.services.ProductQueryService;
import com.greatminds.ayni.product.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.id());
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }
}
