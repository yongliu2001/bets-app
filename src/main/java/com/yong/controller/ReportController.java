package com.yong.controller;

import com.yong.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yongliu on 25/6/18.
 */

@RestController
@RequestMapping("/bets/report")
public class ReportController {

    private final BetService betService;

    @Autowired
    public ReportController(final BetService betService) {
        this.betService = betService;
    }

    @GetMapping("/totalInvestmentPerType")
    List<Object[]>getTotalInvestmentPerTypeReport() {
        return betService.getTotalInvestmentPerType();
    }

    @GetMapping("/totalInvestmentPerCustomer")
    List<Object[]>getTotalInvestmentPerCustomerReport() {
        return betService.getTotalInvestmentPerCustomer();
    }

    @GetMapping("/totalBetsPerType")
    List<Object[]>getTotalBetsPerTypeReport() {
        return betService.getTotalBetsPerType();
    }


    @GetMapping("/totalBetsSoldPerHour")
    List<Object[]>getTotalBetsSoldPerHourReport() {
        return betService.getTotalBetsPerHour();
    }




}
