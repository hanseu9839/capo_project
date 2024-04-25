package com.realworld.feature.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.realworld.feature.file.domain.File;
import com.realworld.feature.file.exception.FileExceptionHandler;
import com.realworld.feature.file.repository.FileRepository;
import com.realworld.feature.image.S3ImageUploader;
import com.realworld.feature.image.ThumbnailImageGenerator;
import com.realworld.global.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
@Slf4j
@Primary
@RequiredArgsConstructor
public class S3StorageService implements StorageService {
    private final AmazonS3 amazonS3;
    private final FileNameGenerator fileNameGenerator;
    private final S3ImageUploader s3ImageUploader;
    private final ThumbnailImageGenerator thumbnailImageGenerator;
    private final FileRepository fileRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public File save(InputStream inputStream, String userId, File file) {
        file.updateId(fileNameGenerator.createFileId());


        // s3 bucket


        try {
            if (file.getContentType().contains("image")) {
                BufferedImage inputImage = ImageIO.read(inputStream);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                try {
                    BufferedImage thumbBufferedImage = thumbnailImageGenerator.thumbnailBufferedImage(inputImage);
                    ImageIO.write(thumbBufferedImage, file.getExtension(), os);
                    inputStream = new ByteArrayInputStream(os.toByteArray());

                    // 이미지 크기 변경
                    file.updateSize(os.size());
                    file.updatePath(s3ImageUploader.upload(amazonS3, bucket, inputStream, file));
                } catch (Exception e) {
                    log.error("Error create thumbnail image", e);
                }
                file.updateHasThumbnail(true);

            } else {
                s3ImageUploader.upload(amazonS3, bucket, inputStream, file);
            }
        } catch (Exception e) {
            log.error("ERROR!!! upload file", e);
            throw new FileExceptionHandler("파일 업로드 중 오류가 발생했습니다.", ErrorCode.BAD_REQUEST_ERROR);
        }

        fileRepository.save(file.toEntity(userId));

        return file;
    }


    @Override
    public void delete(String userId, String fileId) {

    }
}
