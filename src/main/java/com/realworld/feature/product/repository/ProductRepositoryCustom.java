package com.realworld.feature.product.repository;

import com.realworld.feature.product.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> getSearchCardList(Pageable pageable, String search, String category, Long seq);
    
}
