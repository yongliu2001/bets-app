package com.yong.service;

import com.yong.domain.BetRecord;
import com.yong.domain.BetTypes;
import com.yong.repository.BetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by yongliu on 25/6/18.
 */
public class BetServiceImplTest {

    private BetServiceImpl betServiceImp;

    @Mock
    private BetRepository betRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        betServiceImp = new BetServiceImpl(betRepository);
    }

    @Test
    public void testPlaceBetsFailed() throws Exception {

        List<BetRecord> recordList = new ArrayList<>();
        BetRecord record = new BetRecord(1080, BigDecimal.TEN, 104567, BetTypes.QUADDIE, new Date());
        recordList.add(record);
        given(betRepository.saveAll(recordList)).willReturn(new ArrayList<>());

        //when
        boolean attemptResult = betServiceImp.placeBets(recordList);

        //assert
        assertThat(attemptResult).isFalse();
        verify(betRepository).saveAll(recordList);
    }

    @Test
    public void testGetTotalInvestmentPerType() throws Exception {

        //given
        given(betRepository.sumInvestmentGroupByBetTypes()).willReturn(new ArrayList<>());

        //when
        betServiceImp.getTotalInvestmentPerType();

        //verify
        verify(betRepository).sumInvestmentGroupByBetTypes();

    }

    @Test
    public void testGetTotalInvestmentPerCustomer() throws Exception {

        //given
        given(betRepository.sumInvestmentGroupByCustomerId()).willReturn(new ArrayList<>());

        //when
        betServiceImp.getTotalInvestmentPerCustomer();

        //verify
        verify(betRepository).sumInvestmentGroupByCustomerId();
    }

    @Test
    public void testGetTotalBetsPerType() throws Exception {

        //given
        given(betRepository.countBetsGroupByBetTypes()).willReturn(new ArrayList<>());

        //when
        betServiceImp.getTotalBetsPerType();

        //verify
        verify(betRepository).countBetsGroupByBetTypes();
    }

    @Test
    public void tetTotalBetsPerHour() throws Exception {

        //given
        given(betRepository.countBetsGroupByDateHour()).willReturn(new ArrayList<>());

        //when
        betServiceImp.getTotalBetsPerHour();

        //verify
        verify(betRepository).countBetsGroupByDateHour();
    }
}
