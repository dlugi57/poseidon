package com.web.poseidon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Trade {

    @Id
    @NotNull

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( columnDefinition = "TINYINT")
    Integer tradeId;

    @Column(length = 30)
    @NotNull
    @Size(max = 30, message = "account should be maximum 30 characters")
    String account;

    @Column(length = 30)
    @NotNull
    @Size(max = 30, message = "type should be maximum 30 characters")
    String type;
    Double buyQuantity;
    Double sellQuantity;
    Double buyPrice;
    Double sellPrice;

    @Column(length = 125)
    @Size(max = 125, message = "benchmark should be maximum 125 characters")
    String benchmark;

    Timestamp tradeDate;

    @Column(length = 125)
    @Size(max = 125, message = "security should be maximum 125 characters")
    String security;

    @Column(length = 10)
    @Size(max = 10, message = "status should be maximum 10 characters")
    String status;

    @Column(length = 125)
    @Size(max = 125, message = "trader should be maximum 125 characters")
    String trader;

    @Column(length = 125)
    @Size(max = 125, message = "book should be maximum 125 characters")
    String book;

    @Column(length = 125)
    @Size(max = 125, message = "creationName should be maximum 125 characters")
    String creationName;

    Timestamp creationDate;

    @Column(length = 125)
    @Size(max = 125, message = "revisionName should be maximum 125 characters")
    String revisionName;

    Timestamp revisionDate;

    @Column(length = 125)
    @Size(max = 125, message = "dealName should be maximum 125 characters")
    String dealName;

    @Column(length = 125)
    @Size(max = 125, message = "dealType should be maximum 125 characters")
    String dealType;

    @Column(length = 125)
    @Size(max = 125, message = "sourceListId should be maximum 125 characters")
    String sourceListId;

    @Column(length = 125)

    @Size(max = 125, message = "side should be maximum 125 characters")
    String side;


}
