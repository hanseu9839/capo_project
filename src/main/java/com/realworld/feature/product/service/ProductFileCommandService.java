package com.realworld.feature.product.service;

import com.realworld.feature.file.controller.FileResponse;
import com.realworld.feature.file.domain.ProductFile;
import com.realworld.feature.product.domain.Product;

public interface ProductFileCommandService {
    ProductFile save(FileResponse file, Product product);
}
