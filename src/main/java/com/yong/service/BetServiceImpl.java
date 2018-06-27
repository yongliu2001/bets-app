package com.yong.service;

import com.yong.domain.BetRecord;
import com.yong.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yongliu on 25/6/18.
 */
@Service
public class BetServiceImpl implements BetService {


    private BetRepository betRepository;

    @Autowired
    public BetServiceImpl(final BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    /**
     * Place multiple bet records
     * @param records
     * @return boolean
     */
    @Override
    public boolean placeBets(List<BetRecord> records) {
        List<BetRecord> recordList = betRepository.saveAll(records);
        return recordList != null && !recordList.isEmpty();
    }

    /**
     * Get total investment per bet type statistic
     * @return List
     */
    @Override
    public List<Object[]> getTotalInvestmentPerType(){
        return  betRepository.sumInvestmentGroupByBetTypes();
    }

    /**
     * Get total investment per customer statistic
     * @return List
     */
    @Override
    public List<Object[]> getTotalInvestmentPerCustomer() {
        return betRepository.sumInvestmentGroupByCustomerId();
    }

    /**
     * Get total bets per bet type statistic
     * @return List
     */
    @Override
    public List<Object[]> getTotalBetsPerType() {
        return betRepository.countBetsGroupByBetTypes();
    }

    /**
     * Get total bets per hour statistic
     * @return List
     */
    @Override
    public List<Object[]> getTotalBetsPerHour() {
        return betRepository.countBetsGroupByDateHour();
    }


}
