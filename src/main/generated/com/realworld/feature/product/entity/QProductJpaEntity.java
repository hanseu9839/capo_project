package com.realworld.feature.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductJpaEntity is a Querydsl query type for ProductJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductJpaEntity extends EntityPathBase<ProductJpaEntity> {

    private static final long serialVersionUID = 1313152220L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductJpaEntity productJpaEntity = new QProductJpaEntity("productJpaEntity");

    public final EnumPath<com.realworld.global.category.GroupCategory> category = createEnum("category", com.realworld.global.category.GroupCategory.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath hashtag = createString("hashtag");

    public final ListPath<ProductFileJpaEntity, QProductFileJpaEntity> images = this.<ProductFileJpaEntity, QProductFileJpaEntity>createList("images", ProductFileJpaEntity.class, QProductFileJpaEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final com.realworld.feature.member.entity.QMemberJpaEntity member;

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> productSeq = createNumber("productSeq", Long.class);

    public final ComparablePath<java.util.UUID> thumbnailId = createComparable("thumbnailId", java.util.UUID.class);

    public final StringPath title = createString("title");

    public final StringPath userId = createString("userId");

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QProductJpaEntity(String variable) {
        this(ProductJpaEntity.class, forVariable(variable), INITS);
    }

    public QProductJpaEntity(Path<? extends ProductJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductJpaEntity(PathMetadata metadata, PathInits inits) {
        this(ProductJpaEntity.class, metadata, inits);
    }

    public QProductJpaEntity(Class<? extends ProductJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.realworld.feature.member.entity.QMemberJpaEntity(forProperty("member")) : null;
    }

}

