package com.realworld.feature.temporarily_product.service;

import com.realworld.feature.temporarily_product.domain.TemporarilyProduct;

public interface TemporarilyProductQueryService {
    TemporarilyProduct getDetails(Long seq);
}
