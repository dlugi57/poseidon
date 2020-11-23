package com.web.poseidon.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
//@Table(name = "curve_point")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer id;

    @NotNull(message = "curveId must not be null")
    @Column(columnDefinition = "TINYINT")
    Integer curveId;

    Timestamp asOfDate;

    Double term;

    Double value;

    Timestamp creationDate;

}
