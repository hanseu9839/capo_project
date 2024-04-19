package com.realworld.feature.product.service;

import com.realworld.feature.product.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductQueryService {
    List<Product> getSearchProductList(final Pageable pageable, String search, String category, final Long seq);
}
