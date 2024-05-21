package com.realworld.feature.product.service.product;

import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.controller.request.ProductUpdateRequest;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.entity.ProductJpaEntity;
import com.realworld.feature.product.repository.ProductRepository;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomProductExceptionHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository repository;


    @Override
    public Product save(User user, ProductGenerationRequest request) {

        Product product = Product.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(user.getUsername())
                .member(request.getMember())
                .category(request.getCategory())
                .price(request.getPrice())
                .images(request.getImages())
                .thumbnailId(UUID.fromString(request.getThumbnailId()))
                .build();


        return repository.save(product.toEntity()).generationToDomain();
    }

    @Override
    @Transactional
    public Product productUpdates(User user, ProductUpdateRequest request) {
        ProductJpaEntity findProduct = repository.findById(request.getProductSeq()).orElseThrow(() -> new CustomProductExceptionHandler(ErrorCode.NOT_EXISTS_PRODUCT));


        findProduct.setCategory(request.getCategory());
        findProduct.setContent(request.getContent());
        findProduct.setPrice(request.getPrice());
        findProduct.setTitle(request.getTitle());
        findProduct.setThumbnailId(UUID.fromString(request.getThumbnailId()));
        return findProduct.updateToDomain();
    }

    @Override
    public void productDelete(User user, Product product) {
        if (!user.getUsername().equals(product.getUserId())) {
            throw new CustomProductExceptionHandler(ErrorCode.NOT_MATCHES_USER_PRODUCT);
        }

        repository.delete(product.toEntity());
    }
}
