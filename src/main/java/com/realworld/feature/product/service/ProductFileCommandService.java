package com.realworld.feature.product.service;

import com.realworld.feature.file.controller.FileResponse;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.domain.ProductFile;

public interface ProductFileCommandService {
    ProductFile save(FileResponse file, Product product);
}
