package com.realworld.feature.product.service;


import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.domain.Product;

public interface ProductCommandService {
    Product productGeneration(ProductGenerationRequest request);
}
