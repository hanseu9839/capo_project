package com.realworld.feature.temporarily_product.service;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.temporarily_product.controller.request.TemporarilyProductGenerationRequest;
import com.realworld.feature.temporarily_product.domain.TemporarilyProduct;
import com.realworld.feature.temporarily_product.repository.TemporarilyProductRepository;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomProductExceptionHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemporarilyProductCommandServiceImpl implements TemporarilyProductCommandService {
    private final MemberQueryService memberQueryService;
    private final TemporarilyProductRepository repository;

    @Override
    @Transactional
    public TemporarilyProduct productTemporarilyGeneration(User user, TemporarilyProductGenerationRequest request) {
        Member member = memberQueryService.getMemberByUserId(user.getUsername());

        TemporarilyProduct product = TemporarilyProduct.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(member.getUserId())
                .member(member)
                .images(new ArrayList<>())
                .category(request.getCategory())
                .price(request.getPrice())
                .thumbnailId(UUID.fromString(request.getThumbnailId()))
                .build();


        return repository.save(product.toEntity()).generationToDomain();
    }

    @Override
    public void delete(User user, TemporarilyProduct product) {
        if (!user.getUsername().equals(product.getUserId())) {
            throw new CustomProductExceptionHandler(ErrorCode.NOT_MATCHES_USER_PRODUCT);
        }

        repository.delete(product.toEntity());
    }
}
