package com.realworld.feature.card;


import com.realworld.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {
    private final GetCardUseCase getCardUseCase;
    @GetMapping
    public ResponseEntity<ApiResponse> getSearchCardList(@RequestParam(value="seq", required = false)long seq, @RequestParam(required = false) String search,@RequestParam(required = false) String category, @PageableDefault(size = 10, sort="seq", direction =  Sort.Direction.DESC)Pageable pageable){
        getCardUseCase.getSearchCardList(pageable, search, category, seq);
        return null;
    }
}
