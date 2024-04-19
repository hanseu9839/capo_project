package com.realworld.feature.product.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.controller.response.InfiniteProductScrollingResponse;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.service.ProductCommandServiceImpl;
import com.realworld.feature.product.service.ProductQueryServiceImpl;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class ProductController {
    private final ProductQueryServiceImpl productQueryService;
    private final ProductCommandServiceImpl productCommandService;

    /**
     * TODO :: TestCode 작성 및 Product , ProductJpaEntity 추가하기..!
     *
     * @param seq
     * @param search
     * @param category
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<InfiniteProductScrollingResponse>> getSearchCardList(@RequestParam(value = "seq", required = false) Long seq, @RequestParam(required = false) String search, @RequestParam(required = false) String category, @PageableDefault(size = 10, sort = "seq", direction = Sort.Direction.DESC) Pageable pageable) throws JsonProcessingException {
        List<Product> products = productQueryService.getSearchProductList(pageable, search, category, seq);

        InfiniteProductScrollingResponse scrollingResponse = new InfiniteProductScrollingResponse(products);
        scrollingResponse.infiniteIsNext(pageable); // 다음여부 확인 세팅
        scrollingResponse.infiniteLastSeq();

        ApiResponse<InfiniteProductScrollingResponse> apiResponse = new ApiResponse<>(scrollingResponse, SuccessCode.SELECT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());
        // ResponseEntity를 사용하여 ApiResponse 객체를 JSON으로 변환하여 반환
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> productGeneration(@RequestBody @Valid ProductGenerationRequest proGenRequest) {
        Product product = productCommandService.productGeneration(proGenRequest);

        ApiResponse<Product> apiResponse = new ApiResponse<>(product, SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }
}
