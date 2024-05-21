package com.realworld.feature.file.entity;

import com.realworld.feature.file.domain.File;
import com.realworld.feature.member.entity.MemberJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class FileJpaEntity {

    @Id
    @Column
    private UUID id;

    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private MemberJpaEntity member;

    private String path;

    private String name;

    private long size;

    private String extension;

    private boolean hasThumbnail;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public File toDomain() {
        return File.builder()
                .path(this.path)
                .id(this.id)
                .size(this.size)
                .extension(this.extension)
                .hasThumbnail(this.hasThumbnail)
                .name(this.name)
                .build();
    }

}
