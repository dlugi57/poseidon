package com.web.poseidon.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "curve_point")
public class CurvePoint {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer id;

    @Column(columnDefinition = "TINYINT")
    Integer curveId;

    Timestamp asOfDate;

    Double term;

    Double value;

    Timestamp creationDate;

}
