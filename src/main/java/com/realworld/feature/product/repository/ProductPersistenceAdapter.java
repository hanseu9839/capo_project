package com.realworld.feature.product.repository;

import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.entity.ProductJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements CommandProductPort, LoadProductPort {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getSearchCardList(Pageable pageable, String search, String category, long seq) {
        List<ProductJpaEntity> cards = productRepository.getSearchCardList(pageable, search, category, seq);
        List<Product> convertList = new ArrayList<>();
        cards.forEach(card -> convertList.add(card.toDomain()) );
        return convertList;
    }
}
