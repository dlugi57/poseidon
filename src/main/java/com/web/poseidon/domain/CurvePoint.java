package com.web.poseidon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CurvePoint")
public class CurvePoint {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", columnDefinition = "TINYINT")
    Integer id;

    @Column(columnDefinition = "TINYINT")
    Integer curveId;

    Timestamp asOfDate;

    Double term;

    Double value;

    Timestamp creationDate;

}
