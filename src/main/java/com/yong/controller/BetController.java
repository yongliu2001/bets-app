package com.yong.controller;

import com.yong.domain.BetRecord;
import com.yong.service.BetService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yongliu on 25/6/18.
 */

@RestController
@RequestMapping("/bets")
public class BetController {

    private final BetService betService;

    @Autowired
    public BetController(final BetService betService) {
        this.betService = betService;
    }


    @PostMapping("/submit")
    ResponseEntity<ResultResponse> submitBets(@RequestBody List<BetRecord> records) {
        return ResponseEntity.ok(
                new ResultResponse(betService.placeBets(records)));
    }

    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    static final class ResultResponse {

        private final boolean isBetPlaced;
    }

}
