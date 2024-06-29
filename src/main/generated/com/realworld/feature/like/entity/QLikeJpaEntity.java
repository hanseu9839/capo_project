package com.realworld.feature.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeJpaEntity is a Querydsl query type for LikeJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeJpaEntity extends EntityPathBase<LikeJpaEntity> {

    private static final long serialVersionUID = -1831065548L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeJpaEntity likeJpaEntity = new QLikeJpaEntity("likeJpaEntity");

    public final NumberPath<Long> likeSeq = createNumber("likeSeq", Long.class);

    public final com.realworld.feature.member.entity.QMemberJpaEntity member;

    public final com.realworld.feature.product.entity.QProductJpaEntity product;

    public QLikeJpaEntity(String variable) {
        this(LikeJpaEntity.class, forVariable(variable), INITS);
    }

    public QLikeJpaEntity(Path<? extends LikeJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeJpaEntity(PathMetadata metadata, PathInits inits) {
        this(LikeJpaEntity.class, metadata, inits);
    }

    public QLikeJpaEntity(Class<? extends LikeJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.realworld.feature.member.entity.QMemberJpaEntity(forProperty("member"), inits.get("member")) : null;
        this.product = inits.isInitialized("product") ? new com.realworld.feature.product.entity.QProductJpaEntity(forProperty("product"), inits.get("product")) : null;
    }

}

