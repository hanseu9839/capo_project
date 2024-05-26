package com.realworld.feature.temporarily_product.repository;

import com.realworld.feature.temporarily_product.domain.TemporarilyProduct;

public interface TemporarilyProductRepositoryCustom {
    TemporarilyProduct getTemporarilyProductDetails(Long seq);
}
