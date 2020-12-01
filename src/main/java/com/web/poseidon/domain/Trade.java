package com.web.poseidon.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer id;

    @Column(length = 30)
    @NotNull(message = "Account is mandatory")
    @NotBlank(message = "Account is mandatory")
    @Size(max = 30, message = "Account should be maximum 30 characters")
    String account;

    @Column(length = 30)
    @NotNull(message = "Type is mandatory")
    @NotBlank(message = "Type is mandatory")
    @Size(max = 30, message = "Type should be maximum 30 characters")
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

    public Timestamp getTradeDate() {
        if (tradeDate == null) {
            return null;
        }
        return (Timestamp) tradeDate.clone();
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = (Timestamp) tradeDate.clone();
    }

    public Timestamp getCreationDate() {
        if (creationDate == null) {
            return null;
        }
        return (Timestamp) creationDate.clone();
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = (Timestamp) creationDate.clone();
    }

    public Timestamp getRevisionDate() {
        if (revisionDate == null) {
            return null;
        }
        return (Timestamp) revisionDate.clone();
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = (Timestamp) revisionDate.clone();
    }
}
