package com.realworld.project.service.card;

import com.realworld.project.application.port.in.card.GetCardUseCase;
import com.realworld.project.application.port.out.card.CommandCardPort;
import com.realworld.project.application.port.out.card.LoadCardPort;
import com.realworld.project.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CardService implements GetCardUseCase {
    private final CommandCardPort commandCardPort;
    private final LoadCardPort loadCardPort;

    @Autowired
    public CardService(CommandCardPort commandCardPort, LoadCardPort loadCardPort) {
        this.commandCardPort = commandCardPort;
        this.loadCardPort = loadCardPort;
    }

    @Override
    public List<Card> getSearchCardList(Pageable pageable, String search, String category, long seq) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        return loadCardPort.getSearchCardList(pageRequest, search, category , seq);
    }


}
