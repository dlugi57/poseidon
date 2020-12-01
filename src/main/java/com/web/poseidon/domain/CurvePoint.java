package com.web.poseidon.domain;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
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

    public CurvePoint() {
    }

    public Timestamp getAsOfDate() {
        if (asOfDate ==null){
            return null;
        }
        return (Timestamp) asOfDate.clone();
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = (Timestamp) asOfDate.clone();
    }

    public Timestamp getCreationDate() {
        if (creationDate == null){
            return null;
        }
        return (Timestamp) creationDate.clone();
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = (Timestamp) creationDate.clone();
    }
}
