package com.realworld.feature.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFileJpaEntity is a Querydsl query type for FileJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileJpaEntity extends EntityPathBase<FileJpaEntity> {

    private static final long serialVersionUID = -1044480214L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileJpaEntity fileJpaEntity = new QFileJpaEntity("fileJpaEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath extension = createString("extension");

    public final BooleanPath hasThumbnail = createBoolean("hasThumbnail");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final com.realworld.feature.member.entity.QMemberJpaEntity member;

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final StringPath userId = createString("userId");

    public QFileJpaEntity(String variable) {
        this(FileJpaEntity.class, forVariable(variable), INITS);
    }

    public QFileJpaEntity(Path<? extends FileJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFileJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFileJpaEntity(PathMetadata metadata, PathInits inits) {
        this(FileJpaEntity.class, metadata, inits);
    }

    public QFileJpaEntity(Class<? extends FileJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.realworld.feature.member.entity.QMemberJpaEntity(forProperty("member")) : null;
    }

}

