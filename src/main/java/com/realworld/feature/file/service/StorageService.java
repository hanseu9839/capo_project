package com.realworld.feature.file.service;

import com.realworld.feature.file.domain.File;

import java.io.InputStream;

public interface StorageService {
    File save(InputStream inputStream, String userId, File file);
}
