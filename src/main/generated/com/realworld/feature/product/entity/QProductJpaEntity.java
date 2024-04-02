package com.realworld.feature.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductJpaEntity is a Querydsl query type for ProductJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductJpaEntity extends EntityPathBase<ProductJpaEntity> {

    private static final long serialVersionUID = 1313152220L;

    public static final QProductJpaEntity productJpaEntity = new QProductJpaEntity("productJpaEntity");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDt = createDateTime("createDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> productSeq = createNumber("productSeq", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QProductJpaEntity(String variable) {
        super(ProductJpaEntity.class, forVariable(variable));
    }

    public QProductJpaEntity(Path<? extends ProductJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductJpaEntity(PathMetadata metadata) {
        super(ProductJpaEntity.class, metadata);
    }

}

