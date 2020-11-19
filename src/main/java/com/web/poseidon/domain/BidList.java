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
@Table(name = "BidList")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer BidListId;

    @Column(length = 30)
    @NotNull
    @Size(max = 30, message = "account should be maximum 30 characters")
    String account;

    @Column(length = 30)
    @NotNull
    @Size(max = 30, message = "Type should be maximum 30 characters")
    String type;

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


}
