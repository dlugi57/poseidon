package com.web.poseidon.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
public class BidList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer id;

    @Column(length = 30)
    @NotNull(message = "Account is mandatory")
    @NotBlank(message = "Account is mandatory")
    @Size(max = 30, message = "account should be maximum 30 characters")
    String account;

    @Column(length = 30)
    @NotNull(message = "Type is mandatory")
    @NotBlank(message = "Type is mandatory")
    @Size(max = 30, message = "Type should be maximum 30 characters")
    String type;

    Double bidQuantity;
    Double askQuantity;
    Double bid;
    Double ask;

    @Size(max = 125, message = "Benchmark should be maximum 125 characters")
    @Column(length = 125)
    String benchmark;

    Timestamp bidListDate;

    @Size(max = 125, message = "commentary should be maximum 125 characters")
    @Column(length = 125)
    String commentary;

    @Size(max = 125, message = "security should be maximum 125 characters")
    @Column(length = 125)
    String security;

    @Size(max = 10, message = "status should be maximum 10 characters")
    @Column(length = 10)
    String status;

    @Size(max = 125, message = "trader should be maximum 125 characters")
    @Column(length = 125)
    String trader;

    @Size(max = 125, message = "book should be maximum 125 characters")
    @Column(length = 125)
    String book;

    @Size(max = 125, message = "creationName should be maximum 125 characters")
    @Column(length = 125)
    String creationName;

    Timestamp creationDate;

    @Size(max = 125, message = "revisionName should be maximum 125 characters")
    @Column(length = 125)
    String revisionName;

    Timestamp revisionDate;

    @Size(max = 125, message = "dealName should be maximum 125 characters")
    @Column(length = 125)
    String dealName;

    @Size(max = 125, message = "dealType should be maximum 125 characters")
    @Column(length = 125)
    String dealType;

    @Size(max = 125, message = "sourceListId should be maximum 125 characters")
    @Column(length = 125)
    String sourceListId;

    @Size(max = 125, message = "side should be maximum 125 characters")
    @Column(length = 125)
    String side;

    public BidList() {
    }

    public Timestamp getBidListDate() {
        if (bidListDate == null) {
            return null;
        }
        return (Timestamp) bidListDate.clone();
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = (Timestamp) bidListDate.clone();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

}
