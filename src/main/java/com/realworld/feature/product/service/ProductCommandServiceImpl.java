package com.realworld.feature.product.service;

import com.realworld.feature.file.entity.ProductFileJpaEntity;
import com.realworld.feature.file.repository.ProductFileRepository;
import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.entity.ProductJpaEntity;
import com.realworld.feature.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;
    private final MemberQueryService memberQueryService;
    private final ProductFileRepository productFileRepository;

    @Override
    @Transactional
    public Product productGeneration(User user, ProductGenerationRequest request) {
        Member member = memberQueryService.getMemberByUserId(user.getUsername());

        Product product = Product.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(member.getUserId())
                .member(member.toEntity())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();


        return productRepository.save(product.toEntity()).toDomain();
    }

    public Product save(Product product, List<ProductFileJpaEntity> images) {
        ProductJpaEntity productJpaEntity = productRepository.save(product.toEntity());

        
        return productJpaEntity.toDomain();
    }
}
