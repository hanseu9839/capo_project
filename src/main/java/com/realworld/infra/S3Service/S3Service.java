package com.realworld.infra.S3Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    String saveFile(MultipartFile multipartFiles) throws IOException;
}
