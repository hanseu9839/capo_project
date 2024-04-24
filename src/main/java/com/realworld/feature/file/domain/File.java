package com.realworld.feature.file.domain;

import com.realworld.feature.file.controller.FileResponse;
import com.realworld.feature.file.entity.FileJpaEntity;
import com.realworld.feature.file.service.ThumbnailImageGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Getter
@Builder
@ToString
public class File {

    private UUID id;

    private String name;

    private String contentType;
    private String extension;

    private long size;

    private String path;

    private boolean hasThumbnail;

    public void updateHasThumbnail(boolean value) {
        this.hasThumbnail = value;
    }

    public void updatePath(String path) {
        this.path = path;
    }

    public void updateId(UUID id) {
        this.id = id;
    }

    public FileJpaEntity toEntity(String userId) {
        return FileJpaEntity.builder()
                .id(this.id)
                .userId(userId)
                .name(this.name)
                .path(this.path)
                .extension(this.extension)
                .size(this.size)
                .hasThumbnail(this.hasThumbnail)
                .build();
    }

    public FileResponse toResponse() {
        String thumbnailPath = this.hasThumbnail ? (StringUtils.substringBefore(this.path, this.id.toString())
                + ThumbnailImageGenerator.THUMBNAIL_PREFIX + this.id
                + FilenameUtils.EXTENSION_SEPARATOR_STR + ThumbnailImageGenerator.THUMBNAIL_IMAGE_EXTENSION) : "";

        return FileResponse.builder()
                .originalFileName(this.name + FilenameUtils.EXTENSION_SEPARATOR_STR + this.extension)
                .contentType(this.contentType)
                .size(this.size)
                .path(this.path)
                .hasThumbnail(this.hasThumbnail)
                .thumbnailPath(thumbnailPath)
                .build();
    }
}
