package com.realworld.feature.file.service;

import com.realworld.feature.file.domain.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    File createFile(InputStream inputStream, String userId, File file);

    String makeFileName(MultipartFile mpf);
}
