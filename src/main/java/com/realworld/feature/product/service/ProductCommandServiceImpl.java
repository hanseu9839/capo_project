package com.realworld.feature.product.service;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.controller.request.ProductUpdateRequest;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.entity.ProductJpaEntity;
import com.realworld.feature.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;
    private final MemberQueryService memberQueryService;


    @Override
    @Transactional
    public Product productGeneration(User user, ProductGenerationRequest request) {
        Member member = memberQueryService.getMemberByUserId(user.getUsername());

        Product product = Product.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(member.getUserId())
                .member(member)
                .images(new ArrayList<>())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();


        return productRepository.save(product.toEntity()).toDomain();
    }

    @Override
    @Transactional
    public Product productUpdates(User user, ProductUpdateRequest request) {
        Optional<ProductJpaEntity> productEntity = productRepository.findById(String.valueOf(request.getProductSeq()));
        log.info("productEntity :: {}", productEntity.get());
        return null;
    }
}
