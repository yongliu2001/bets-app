package com.yong.service;

import com.yong.domain.BetRecord;

import java.util.List;

/**
 * Created by yongliu on 25/6/18.
 */
public interface BetService {


    /**
     * save bet records to db
     * @param records
     * @return
     */
    boolean placeBets(List<BetRecord> records);

    List<Object[]> getTotalInvestmentPerType();

    List<Object[]> getTotalInvestmentPerCustomer();

    List<Object[]> getTotalBetsPerType();

    List<Object[]> getTotalBetsPerHour();



}
