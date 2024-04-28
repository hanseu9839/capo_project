package com.realworld.infra.aws;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.realworld.feature.file.domain.File;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class AwsService {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    
    public String uploadS3Bucket(InputStream inputStream, File file) {
        String originFilename = file.getName();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucket, originFilename, inputStream, metadata);

        return amazonS3.getUrl(bucket, originFilename).toString();
    }

    public void deleteS3Bucket(String key) {
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, key);
        amazonS3.deleteObject(request);
    }
}
