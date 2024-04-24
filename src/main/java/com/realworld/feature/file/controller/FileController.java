package com.realworld.feature.file.controller;

import com.realworld.feature.file.domain.File;
import com.realworld.feature.file.service.FileNameGenerator;
import com.realworld.feature.file.service.StorageService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final StorageService localStorageService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<List<FileResponse>>> uploadFiles(@AuthenticationPrincipal User user, @RequestParam(name = "file") MultipartFile[] multipartFiles) throws IOException {
        List<FileResponse> fileResponseList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            String contentType = URLConnection.guessContentTypeFromStream(new BufferedInputStream(multipartFile.getInputStream()));
            if (contentType == null) {
                contentType = multipartFile.getContentType();
            }

            FileNameGenerator fileNameGenerator = new FileNameGenerator();
            String fileName = fileNameGenerator.getMultipartFileName(multipartFile);

            String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            File file = File.builder()
                    .name(fileName)
                    .size(multipartFile.getSize())
                    .extension(fileExtension)
                    .contentType(contentType)
                    .build();

            try (InputStream inputStream = multipartFile.getInputStream()) {
                File savedFile = localStorageService.save(inputStream, user.getUsername(), file);
                fileResponseList.add(savedFile.toResponse());
            }
        }

        ApiResponse<List<FileResponse>> fileUploadResponse = new ApiResponse<>(fileResponseList,
                SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.INSERT_SUCCESS.getMessage());

        return ResponseEntity.status(HttpStatus.CREATED).body(fileUploadResponse);
    }

}
