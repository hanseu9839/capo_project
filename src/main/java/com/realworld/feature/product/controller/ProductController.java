package com.realworld.feature.product.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.realworld.feature.file.controller.FileResponse;
import com.realworld.feature.file.domain.File;
import com.realworld.feature.file.domain.ProductFile;
import com.realworld.feature.file.service.FileNameGenerator;
import com.realworld.feature.file.service.StorageService;
import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.controller.response.InfiniteProductScrollingResponse;
import com.realworld.feature.product.controller.response.ProductGenerationResponse;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.service.ProductCommandService;
import com.realworld.feature.product.service.ProductFileCommandService;
import com.realworld.feature.product.service.ProductQueryService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class ProductController {
    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;
    private final ProductFileCommandService productFileCommandService;
    private final StorageService cloudStorageService;


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
    public ResponseEntity<ApiResponse<ProductGenerationResponse>> productGeneration(@AuthenticationPrincipal User user, @RequestPart @Valid ProductGenerationRequest proGenRequest, @RequestPart(name = "images") List<MultipartFile> multipartFiles) throws IOException {

        Product product = productCommandService.productGeneration(user, proGenRequest);

        List<FileResponse> fileResponseList = new ArrayList<>();
        File file = null;
        for (MultipartFile multipartFile : multipartFiles) {

            String contentType = URLConnection.guessContentTypeFromStream(new BufferedInputStream(multipartFile.getInputStream()));
            if (contentType == null) {
                contentType = multipartFile.getContentType();
            }

            FileNameGenerator fileNameGenerator = new FileNameGenerator();
            String fileName = fileNameGenerator.getMultipartFileName(multipartFile);

            String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            file = File.builder()
                    .name(fileName)
                    .size(multipartFile.getSize())
                    .extension(fileExtension)
                    .contentType(contentType)
                    .build();


//            ProductFile file = ProductFile.builder()
//                    .name(fileName)
//                    .userId(user.getUsername())
//                    .product(product)
//                    .size(multipartFiles.size())
//                    .extension(fileExtension)
//                    .contentType(contentType)
//                    .build();

            try (InputStream inputStream = multipartFile.getInputStream()) {
                File savedFile = cloudStorageService.upload(inputStream, user.getUsername(), file);
                fileResponseList.add(savedFile.toResponse());
            }
        }

        List<ProductFile> images = new ArrayList<>();
        fileResponseList.forEach(fileResponse -> images.add(productFileCommandService.save(fileResponse, product)));

        ProductGenerationResponse response = ProductGenerationResponse.builder()
                .productSeq(product.getProductSeq())
                .title(product.getTitle())
                .userId(product.getUserId())
                .content(product.getContent())
                .category(product.getCategory())
                .price(product.getPrice())
                .views(product.getViews())
                .createDt(product.getCreateDt())
                .regDt(product.getRegDt())
                .images(images)
                .member(product.getMember())
                .build();

        ApiResponse<ProductGenerationResponse> apiResponse = new ApiResponse<>(response, SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }
}
