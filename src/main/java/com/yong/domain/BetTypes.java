package com.yong.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * BetTypes = WIN / PLACE / TRIFECTA / DOUBLE / QUADDIE
 * <p>
 * Created by yongliu on 25/6/18.
 */



@RequiredArgsConstructor
@Getter
@ToString
public enum BetTypes {

    WIN("WIN"),
    PLACE("PLACE"),
    TRIFECTA("TRIFECTA"),
    DOUBLE("DOUBLE"),
    QUADDIE("QUADDIE");

    private String type;

    BetTypes(String type) {
        this.type = type;
    }


}
