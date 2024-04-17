package com.realworld.feature.product.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.entity.ProductJpaEntity;
import com.realworld.feature.product.entity.QProductJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QProductJpaEntity product = QProductJpaEntity.productJpaEntity;

    @Override
    public List<Product> getSearchCardList(Pageable pageable, String search, String category, Long seq) {

        List<ProductJpaEntity> products = queryFactory
                .select(
                        Projections.fields(ProductJpaEntity.class,
                                product.productSeq,
                                product.title,
                                product.price,
                                product.views,
                                product.regDt
                        ))
                .from(product)
                .where(
                        ltSeq(seq),
                        containTitle(search),
                        eqCategory(category)
                )
                .orderBy(productSort(pageable))
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return products.stream().map(ProductJpaEntity::toDomain).toList();
    }

    private BooleanExpression containTitle(String search) {
        if (search == null || search.isEmpty()) {
            return null;
        }
        return product.title.containsIgnoreCase(search);
    }

    private BooleanExpression eqCategory(String category) {
        if (category == null || category.isEmpty()) {
            return null;
        }

        return product.category.eq(category);
    }

    private BooleanExpression ltSeq(Long seq) {
        if (seq == null) {
            return null; // BooleanExpression 자리에 NULL 반환 시 자동으로 제거된다.
        }
        return product.productSeq.lt(seq);
    }

    private OrderSpecifier<?> productSort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                return switch (order.getProperty()) {
                    case "title" -> new OrderSpecifier<>(direction, product.title);
                    case "regDt" -> new OrderSpecifier<>(direction, product.regDt);
                    case "createDt" -> new OrderSpecifier<>(direction, product.createDt);
                    case "views" -> new OrderSpecifier<>(direction, product.views);
                    default -> new OrderSpecifier<>(direction, product.productSeq);
                };
            }
        }
        return new OrderSpecifier<>(Order.DESC, product.productSeq);
    }
}
