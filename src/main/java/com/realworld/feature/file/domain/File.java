package com.realworld.feature.file.domain;

import com.realworld.feature.file.entity.FileJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
                .build();
    }
}
