package com.realworld.feature.product.controller;


import com.realworld.feature.product.controller.request.ProductGenerationRequest;
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
    public ResponseEntity<ApiResponse<Object>> getSearchCardList(@RequestParam(value = "seq", required = false) long seq, @RequestParam(required = false) String search, @RequestParam(required = false) String category, @PageableDefault(size = 10, sort = "seq", direction = Sort.Direction.DESC) Pageable pageable) {
        List<Product> products = productQueryService.getSearchProductList(pageable, search, category, seq);

        ApiResponse<Object> apiResponse = new ApiResponse<>(products, SuccessCode.SELECT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> productGeneration(@RequestBody @Valid ProductGenerationRequest proGenRequest) {
        Product product = productCommandService.productGeneration(proGenRequest);
        log.info("product :: {}", product);
        return ResponseEntity.ok(null);
    }
}
