package com.realworld.feature.product.service;

import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl {
    ProductRepository productRepository;

    public Product productGeneration(ProductGenerationRequest request) {
        Product product = Product.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();


        return productRepository.save(product.toEntity()).toDomain();
    }
}
