package com.yong.controller;

import com.yong.domain.BetTypes;
import com.yong.service.BetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by yongliu on 27/6/18.
 */
public class ReportControllerTest {

    @Mock
    private BetService betService;

    private ReportController reportController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        reportController = new ReportController(betService);
    }

    @Test
    public void getTotalInvestmentPerTypeReport() throws Exception {

        List<Object[]> totalInvestmentPerTypeReport = new ArrayList<>();
        Object[] typeReport1 = {BetTypes.TRIFECTA, BigDecimal.TEN};
        Object[] typeReport2 = {BetTypes.WIN, BigDecimal.ONE};

        totalInvestmentPerTypeReport.add(typeReport1);
        totalInvestmentPerTypeReport.add(typeReport2);

        //given
        given(betService.getTotalInvestmentPerType()).willReturn(totalInvestmentPerTypeReport);
        //when
        List<Object[]> report = reportController.getTotalInvestmentPerTypeReport();
        //assert
        assertEquals(report.size(),  2);

    }

    @Test
    public void getTotalInvestmentPerCustomerReport() throws Exception {
        List<Object[]> totalInvestmentPerCustomerReport = new ArrayList<>();
        Object[] report1 = {1000, BigDecimal.TEN};
        Object[] report2 = {1001, BigDecimal.ONE};

        totalInvestmentPerCustomerReport.add(report1);
        totalInvestmentPerCustomerReport.add(report2);

        //given
        given(betService.getTotalInvestmentPerCustomer()).willReturn(totalInvestmentPerCustomerReport);
        //when
        List<Object[]> report = reportController.getTotalInvestmentPerCustomerReport();
        //assert
        assertEquals(report.size(),  2);
    }

    @Test
    public void getTotalBetsPerTypeReport() throws Exception {

        List<Object[]> totalBetsPerTypeReport = new ArrayList<>();
        //given
        given(betService.getTotalBetsPerType()).willReturn(totalBetsPerTypeReport);
        //when
        List<Object[]> report = reportController.getTotalBetsPerTypeReport();
        //assert
        assertEquals(report.size(),  0);
    }

    @Test
    public void getTotalBetsSoldPerHourReport() throws Exception {

        List<Object[]> totalBetsSoldPerHourReport = new ArrayList<>();

        Object[] report1 = {"2018-01-01 00",10};
        Object[] report2 = {"2018-01-01 01",20};
        Object[] report3 = {"2018-01-01 02",30};

        totalBetsSoldPerHourReport.add(report1);
        totalBetsSoldPerHourReport.add(report2);
        totalBetsSoldPerHourReport.add(report3);

        //given
        given(betService.getTotalBetsPerHour()).willReturn(totalBetsSoldPerHourReport);
        //when
        List<Object[]> report = reportController.getTotalBetsSoldPerHourReport();
        //assert
        assertEquals(3, report.size());
    }

}