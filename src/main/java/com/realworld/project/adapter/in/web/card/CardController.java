package com.realworld.project.adapter.in.web.card;


import com.realworld.project.application.port.in.card.GetCardUseCase;
import com.realworld.project.application.port.in.dto.CardDto;
import com.realworld.project.common.code.SuccessCode;
import com.realworld.project.common.response.ApiResponse;
import com.realworld.project.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {
    private final GetCardUseCase getCardUseCase;

    /**
     * TODO :: TestCode 작성 및 Card , CardJpaEntity 추가하기..!
     * @param seq
     * @param search
     * @param category
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getSearchCardList(@RequestParam(value="seq", required = false)long seq, @RequestParam(required = false) String search,@RequestParam(required = false) String category, @PageableDefault(size = 10, sort="seq", direction =  Sort.Direction.DESC)Pageable pageable){
        List<Card> cards = getCardUseCase.getSearchCardList(pageable, search, category, seq);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cards)
                .resultCode(SuccessCode.SELECT_SUCCESS.getStatus())
                .resultMsg(SuccessCode.SELECT_SUCCESS.getMessage())
                .build(), HttpStatus.OK);
    }


}
