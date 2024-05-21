package com.realworld.project.product;

import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.repository.ProductRepository;
import com.realworld.global.category.GroupCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@SpringBootTest
public class productTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void firstPagingOffset() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10);
        // when
        List<Product> list = productRepository.getSearchProductList(pageRequest, "", GroupCategory.valueOf("BOY_GROUP"), null);

//        for(Product product: list){
//            System.out.println(product.getProductSeq());
//            System.out.println(product.getViews());
//        }

        PageRequest pageRequest2 = PageRequest.of(1, 10);

//        List<Product> list2 = productRepository.getSearchProductList(pageRequest2, "", GroupCategory.valueOf(""), 14L);

//        for(ProductJpaEntity productJpaEntity : list2){
//            System.out.println(productJpaEntity.getProductSeq());
//        }

        // then
    }
}
