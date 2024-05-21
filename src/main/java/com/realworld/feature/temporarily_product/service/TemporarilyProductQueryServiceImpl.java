package com.realworld.feature.temporarily_product.service;

import com.realworld.feature.temporarily_product.domain.TemporarilyProduct;
import com.realworld.feature.temporarily_product.repository.TemporarilyProductRepository;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomProductExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemporarilyProductQueryServiceImpl implements TemporarilyProductQueryService {
    private final TemporarilyProductRepository repository;

    @Override
    public TemporarilyProduct getDetails(Long seq) {
        return repository.findById(seq).orElseThrow(() -> new CustomProductExceptionHandler(ErrorCode.NOT_EXISTS_PRODUCT)).toDomain();
    }
}
