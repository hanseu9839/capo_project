package com.realworld.feature.temporarily_product.service;

import com.realworld.feature.temporarily_product.domain.TemporarilyProduct;
import com.realworld.feature.temporarily_product.domain.TemporarilyProductFile;
import com.realworld.feature.temporarily_product.repository.TemporarilyProductFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemporarilyProductFileCommandServiceImpl implements TemporarilyProductFileCommandService {

    private final TemporarilyProductFileRepository repository;

    @Override
    public TemporarilyProductFile save(String imageId, TemporarilyProduct product) {
        TemporarilyProductFile file = TemporarilyProductFile.builder()
                .id(UUID.fromString(imageId))
                .userId(product.getUserId())
                .product(product)
                .build();
        return repository.save(file.toEntity()).generationToDomain();
    }
}
