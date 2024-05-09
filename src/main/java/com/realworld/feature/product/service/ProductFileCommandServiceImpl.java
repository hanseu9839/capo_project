package com.realworld.feature.product.service;

import com.realworld.feature.file.controller.FileResponse;
import com.realworld.feature.file.repository.ProductFileRepository;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.domain.ProductFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class ProductFileCommandServiceImpl implements ProductFileCommandService {
    private final ProductFileRepository productFileRepository;

    @Override
    public ProductFile save(FileResponse file, Product product) {
        ProductFile productFile = ProductFile.builder()
                .id(UUID.fromString(file.getId()))
                .userId(product.getUserId())
                .product(product)
                .build();
        return productFileRepository.save(productFile.toEntity()).toDomain();
    }
}
