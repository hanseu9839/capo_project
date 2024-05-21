package com.realworld.feature.temporarily_product.controller;

import com.realworld.feature.temporarily_product.controller.request.TemporarilyProductGenerationRequest;
import com.realworld.feature.temporarily_product.controller.response.TemporarilyProductGenerationResponse;
import com.realworld.feature.temporarily_product.domain.TemporarilyProduct;
import com.realworld.feature.temporarily_product.domain.TemporarilyProductFile;
import com.realworld.feature.temporarily_product.service.TemporarilyProductCommandService;
import com.realworld.feature.temporarily_product.service.TemporarilyProductFileCommandService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards/temporarily")
public class TemporarilyProductController {
    private final TemporarilyProductCommandService temporarilyProductCommandService;
    private final TemporarilyProductFileCommandService temporarilyProductFileCommandService;

    public ResponseEntity<ApiResponse<TemporarilyProductGenerationResponse>> temporarilyProductGeneration(@AuthenticationPrincipal User user, @RequestBody @Valid TemporarilyProductGenerationRequest request) throws IOException {

        TemporarilyProduct product = temporarilyProductCommandService.productTemporarilyGeneration(user, request);

        List<TemporarilyProductFile> images = new ArrayList<>();

        if (!request.getImages().isEmpty())
            request.getImages().forEach(imageId -> images.add(temporarilyProductFileCommandService.save(imageId, product)));


        TemporarilyProductGenerationResponse response = TemporarilyProductGenerationResponse.builder()
                .productSeq(product.getProductSeq())
                .title(product.getTitle())
                .userId(product.getUserId())
                .content(product.getContent())
                .category(product.getCategory())
                .price(product.getPrice())
                .thumbnailId(String.valueOf(product.getThumbnailId()))
                .createAt(product.getCreatedAt())
                .modifiedAt(product.getModifiedAt())
                .images(images)
                .member(product.getMember())
                .build();

        ApiResponse<TemporarilyProductGenerationResponse> apiResponse = new ApiResponse<>(response, SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{temporarily_product_seq}")
    public ResponseEntity<ApiResponse<?>> temporarilyProductDeletes(@AuthenticationPrincipal User user, @PathVariable(value = "temporarily_product_seq") Long seq) {

    }
}
