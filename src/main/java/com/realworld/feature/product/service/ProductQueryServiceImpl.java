package com.realworld.feature.product.service;

import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.repository.LoadProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductQueryServiceImpl implements ProductQueryService{
    private final LoadProductPort loadProductPort;
    @Override
    public List<Product> getSearchProductList(Pageable pageable, String search, String category, long seq) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        return loadProductPort.getSearchCardList(pageRequest, search, category , seq);
    }

}
