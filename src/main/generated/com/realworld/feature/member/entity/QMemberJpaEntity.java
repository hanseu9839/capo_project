package com.realworld.feature.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberJpaEntity is a Querydsl query type for MemberJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberJpaEntity extends EntityPathBase<MemberJpaEntity> {

    private static final long serialVersionUID = 1681999534L;

    public static final QMemberJpaEntity memberJpaEntity = new QMemberJpaEntity("memberJpaEntity");

    public final EnumPath<com.realworld.feature.auth.Authority> authority = createEnum("authority", com.realworld.feature.auth.Authority.class);

    public final DateTimePath<java.time.LocalDateTime> createDt = createDateTime("createDt", java.time.LocalDateTime.class);

    public final StringPath delYn = createString("delYn");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userId = createString("userId");

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public QMemberJpaEntity(String variable) {
        super(MemberJpaEntity.class, forVariable(variable));
    }

    public QMemberJpaEntity(Path<? extends MemberJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberJpaEntity(PathMetadata metadata) {
        super(MemberJpaEntity.class, metadata);
    }

}

