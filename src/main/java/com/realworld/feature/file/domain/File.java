package com.realworld.feature.file.domain;

import com.realworld.feature.file.controller.FileResponse;
import com.realworld.feature.file.entity.FileJpaEntity;
import com.realworld.feature.image.ThumbnailImageGenerator;
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
    private String thumbnailPath;

    public void updateHasThumbnail(boolean value) {
        this.hasThumbnail = value;
    }

    public void updatePath(String path) {
        this.path = path;
    }

    public void updateSize(int size) {
        this.size = size;
    }

    public String getThumbnailPath() {
        return isHasThumbnail() ? (StringUtils.substringBefore(this.path, this.id.toString())
                + ThumbnailImageGenerator.THUMBNAIL_PREFIX + this.id) : "";
    }

    public void updateId(UUID id) {
        this.id = id;
    }

    public FileJpaEntity toEntity(String userId) {
        return FileJpaEntity.builder()
                .id(getId())
                .userId(userId)
                .name(getName())
                .path(getPath())
                .extension(getExtension())
                .size(getSize())
                .hasThumbnail(isHasThumbnail())
                .build();
    }

    public FileResponse toResponse() {
        return FileResponse.builder()
                .id(String.valueOf(getId()))
                .originalFileName(getName() + FilenameUtils.EXTENSION_SEPARATOR_STR + getExtension())
                .contentType(getContentType())
                .size(getSize())
                .path(getPath())
                .hasThumbnail(isHasThumbnail())
                .thumbnailPath(getThumbnailPath())
                .build();
    }
}
