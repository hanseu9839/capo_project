package com.realworld.product;

import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.entity.ProductJpaEntity;
import com.realworld.feature.product.repository.ProductRepository;
import com.realworld.feature.product.service.ProductQueryService;
import com.realworld.feature.product.service.ProductQueryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;




@SpringBootTest
public class productTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void firstPagingOffset() {
        // given
        PageRequest pageRequest = PageRequest.of(0,10);
        // when
        List<ProductJpaEntity> list= productRepository.getSearchCardList(pageRequest, "", "", null);

//        for(ProductJpaEntity productJpaEntity : list){
//            System.out.println(productJpaEntity.getProductSeq());
//            System.out.println(productJpaEntity.getViews());
//        }

        PageRequest pageRequest2 = PageRequest.of(1,10);

        List<ProductJpaEntity> list2 = productRepository.getSearchCardList(pageRequest2, "","",14L);

//        for(ProductJpaEntity productJpaEntity : list2){
//            System.out.println(productJpaEntity.getProductSeq());
//        }

        // then
    }
}
