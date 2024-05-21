package com.realworld.feature.product.service.product;


import com.realworld.feature.product.controller.request.ProductGenerationRequest;
import com.realworld.feature.product.controller.request.ProductUpdateRequest;
import com.realworld.feature.product.domain.Product;
import org.springframework.security.core.userdetails.User;

public interface ProductCommandService {

    Product save(User user, ProductGenerationRequest request);

    Product productUpdates(User user, ProductUpdateRequest request);

    void productDelete(User user, Product product);
}
