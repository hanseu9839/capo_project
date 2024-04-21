package com.realworld.feature.file.service;

import com.realworld.feature.file.domain.File;
import com.realworld.feature.file.repository.FileRepository;
import com.realworld.global.utils.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    final private FileRepository fileRepository;

    @Value("${file.path}")
    private String filePath;

    @Override
    public File createFile(InputStream inputStream, String userId, File file) {

        UUID uuid = UUIDGenerator.createUUIDV2();
        file.updateId(uuid);

        try {
            // TODO: 이미지 파일일 경우 썸네일 이미지 생성
//            try {
//                BufferedImage image = ImageIO.read(inputStream);
//            } catch (Exception e) {
//                log.error("thumbnail create error", e);
//            }

            String savedFileName = uuid + FilenameUtils.EXTENSION_SEPARATOR_STR + file.getExtension();
            file.updatePath(filePath + java.io.File.separator + savedFileName);

            FileUtils.copyInputStreamToFile(inputStream, new java.io.File(filePath, savedFileName));

            fileRepository.save(file.toEntity(userId));

        } catch (IOException e) {
            // TODO: throwError
        }

        return file;
    }

    @Override
    public String makeFileName(MultipartFile mpf) {
        String fileName = new String(Objects.requireNonNull(mpf.getOriginalFilename()).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        return FilenameUtils.getBaseName(fileName);
    }
}
