package com.realworld.project.adapter.out.persistence.card;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CardRepositoryImpl implements  CardRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final QCardJpaEntity card = QCardJpaEntity.cardJpaEntity;
    @Override
    public Slice<CardJpaEntity> findAllCardList(Pageable pageable, String search, String category, long seq) {
        List<CardJpaEntity> cards = queryFactory
                .select(
                        Projections.fields(CardJpaEntity.class,
                        card.cardSeq,
                        card.title,
                        card.price,
                        card.views,
                        card.regDt
                        ))
                .from(card)
                .where(
                    containTitle(search),
                    eqCategory(category)
                )
                .orderBy()
                .limit(pageable.getPageSize()+1)
                .fetch();
        return null;
    }

    private BooleanExpression containTitle(String search){
        if(search == null || search.isEmpty()) {
            return null;
        }
        return card.title.containsIgnoreCase(search);
    }

    private BooleanExpression eqCategory(String category){
        if(category == null || category.isEmpty()){
            return null;
        }

        return card.category.eq(category);
    }

    private BooleanExpression ltSeq(Long seq){
        if(seq == null){
            return null;
        }
        return null;
    }

    private OrderSpecifier<?> cardSort(Pageable pageable){
        if(!pageable.getSort().isEmpty()){
            for(Sort.Order order: pageable.getSort()){
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch(order.getProperty()){
                    case "title" :
                        return new OrderSpecifier<>(direction, card.title);
                    case "seq" :
                        return new OrderSpecifier<>(direction, card.cardSeq);
                    case "regDt" :
                        return new OrderSpecifier<>(direction, card.regDt);
                    case "createDt" :
                        return new OrderSpecifier<>(direction, card.createDt);
                    case "views" :
                        return new OrderSpecifier<>(direction, card.views);
                }
            }
        }
        return null;
    }
}
