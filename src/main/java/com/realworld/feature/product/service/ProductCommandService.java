package com.realworld.feature.product.service;


import com.realworld.feature.file.entity.ProductFileJpaEntity;
import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.domain.Product;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface ProductCommandService {
    Product productGeneration(User user, ProductGenerationRequest request);

    Product save(Product product, List<ProductFileJpaEntity> images);
}
