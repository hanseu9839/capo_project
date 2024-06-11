package com.realworld.feature.file.service;

import com.realworld.feature.file.domain.File;

import java.io.InputStream;
import java.io.OutputStream;

public interface StorageService {
    File upload(InputStream inputStream, String userId, File file);

    void delete(String userId, String fileId);

    String getFile(String id, OutputStream os);
}
