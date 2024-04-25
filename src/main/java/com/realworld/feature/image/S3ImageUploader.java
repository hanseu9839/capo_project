package com.realworld.feature.image;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.realworld.feature.file.domain.File;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class S3ImageUploader {
    
    public String upload(AmazonS3 amazonS3, String bucket, InputStream inputStream, File file) {
        String originFilename = file.getName();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucket, originFilename, inputStream, metadata);

        return amazonS3.getUrl(bucket, originFilename).toString();
    }
}
