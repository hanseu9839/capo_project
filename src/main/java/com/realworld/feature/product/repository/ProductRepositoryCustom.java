package com.realworld.feature.product.repository;

import com.realworld.feature.product.entity.ProductJpaEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductJpaEntity> getSearchCardList(Pageable pageable, String search, String category, long seq);
}
