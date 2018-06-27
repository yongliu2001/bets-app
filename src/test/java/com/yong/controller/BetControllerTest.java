package com.yong.controller;

import com.yong.domain.BetRecord;
import com.yong.domain.BetTypes;
import com.yong.service.BetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

/**
 * Created by yongliu on 26/6/18.
 */

public class BetControllerTest {

    @Mock
    private BetService betService;

    private BetController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new BetController(betService);
    }

    @Test
    public void testSubmitBets() throws Exception {

        List<BetRecord> recordList = new ArrayList<>();
        BetRecord record = new BetRecord(1080, BigDecimal.TEN, 104567, BetTypes.QUADDIE, new Date());
        recordList.add(record);

        //given
        given(betService.placeBets(recordList)).willReturn(true);

        //when
        ResponseEntity<BetController.ResultResponse> response = controller.submitBets(recordList);


        //assert
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertTrue(response.getBody().isBetPlaced());
    }

}