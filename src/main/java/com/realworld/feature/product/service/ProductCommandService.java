package com.realworld.feature.product.service;


import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.controller.request.ProductUpdateRequest;
import com.realworld.feature.product.domain.Product;
import org.springframework.security.core.userdetails.User;

public interface ProductCommandService {
    Product productGeneration(User user, ProductGenerationRequest request);

    Product productUpdates(User user, ProductUpdateRequest request);

}
