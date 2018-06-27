package com.yong.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yongliu on 25/6/18.
 */


/* bet record json format
{
	“DateTime” : “2018-01-01 14:56”
	“BetType”: “TRIFECTA”,
	“PropNumber”: 104567,
	“CustomerID” : 1080,
	“Investment”: 100.00
}

 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "BET_RECORD")
public final class BetRecord {

    @Id
    @GeneratedValue
    @Column(name = "BET_ID")
    private Long id;

    @JsonProperty("CustomerID")
    @Min(value = 0, message = "customer id is invalid")
    private final int customId;

    @Digits(integer=9, fraction=2)
    @DecimalMin(value = "1.00")
    @NotNull
    @JsonProperty("Investment")
    private final BigDecimal investment;

    @JsonProperty("PropNumber")
    @Min(value= 0, message = "Prop Number is invalid")
    private final int propNumber;


    @Enumerated(EnumType.STRING)
    @JsonProperty("BetType")
    @NotNull
    private final BetTypes betTypes;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm")
    @JsonProperty("DateTime")
    @NotNull
    private final Date betDateTime;

    //empty constructor for json serialization
    public BetRecord() {
        this(0, BigDecimal.ONE, 0, null,  new Date());
    }

}
