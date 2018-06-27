package com.yong.repository;

import com.yong.domain.BetRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yongliu on 25/6/18.
 */
public interface BetRepository extends JpaRepository<BetRecord, Long> {


    @Query(value= "SELECT BET_TYPES as type, SUM (INVESTMENT)"
            + "FROM BET_RECORD GROUP BY BET_TYPES", nativeQuery = true)
    public List<Object[]>sumInvestmentGroupByBetTypes();


    @Query(value= "SELECT CUSTOM_ID as customer, SUM (INVESTMENT)"
            + "FROM BET_RECORD GROUP BY CUSTOM_ID", nativeQuery = true)
    public List<Object[]>sumInvestmentGroupByCustomerId();

    @Query(value= "SELECT BET_TYPES as type, COUNT (BET_ID)"
            + "FROM BET_RECORD GROUP BY BET_TYPES", nativeQuery = true)
    public List<Object[]>countBetsGroupByBetTypes();

    @Query(value= "SELECT TO_CHAR(\"BET_DATE_TIME\",\'YYYY-MM-DD HH\') as hour, COUNT (BET_ID)"
            + "FROM BET_RECORD GROUP BY TO_CHAR(\"BET_DATE_TIME\",\'YYYY-MM-DD HH\')", nativeQuery = true)
    public List<Object[]>countBetsGroupByDateHour();

}
