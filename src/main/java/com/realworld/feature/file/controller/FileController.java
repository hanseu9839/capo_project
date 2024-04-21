package com.realworld.feature.file.controller;

import com.realworld.feature.file.domain.File;
import com.realworld.feature.file.service.FileService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@Slf4j
@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> uploadFiles(@AuthenticationPrincipal User user, @RequestParam(name = "file") MultipartFile[] multipartFiles) throws IOException {

        for (MultipartFile multipartFile : multipartFiles) {
            String contentType = URLConnection.guessContentTypeFromStream(new BufferedInputStream(multipartFile.getInputStream()));
            if (contentType == null) {
                contentType = multipartFile.getContentType();
            }

            String fileName = fileService.makeFileName(multipartFile);
            String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            File file = File.builder()
                    .name(fileName)
                    .size(multipartFile.getSize())
                    .extension(fileExtension)
                    .contentType(contentType)
                    .build();

            try (InputStream inputStream = multipartFile.getInputStream()) {
                fileService.createFile(inputStream, user.getUsername(), file);
            }
        }

        ApiResponse<?> fileUploadResponse = new ApiResponse<>(null,
                SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.INSERT_SUCCESS.getMessage());

        return ResponseEntity.status(HttpStatus.CREATED).body(fileUploadResponse);
    }

}
