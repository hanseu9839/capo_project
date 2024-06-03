package com.realworld.infra.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FireBaseService {

    @Value("${firebase.storage-name}")
    private String fireBaseStorage;

    public String uploadFireBaseBucket(InputStream inputStream, String key, long fileSize, String contentType) {
        Bucket bucket = StorageClient.getInstance().bucket(fireBaseStorage);
        Blob blob = bucket.create(key, inputStream, contentType);

        return blob.getMediaLink();
    }

    public void getFile(String key, OutputStream os) {
        Bucket bucket = StorageClient.getInstance().bucket(fireBaseStorage);

        Blob blob = bucket.get(key);
        blob.downloadTo(os);
    }

    public void deleteFireBaseBucket(String key) {
        Bucket bucket = StorageClient.getInstance().bucket(fireBaseStorage);
        bucket.get(key).delete();
    }
}
