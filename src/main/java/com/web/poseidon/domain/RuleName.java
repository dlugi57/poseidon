package com.web.poseidon.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RuleName {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer id;


    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "name should be maximum 125 characters")
    String name;


    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "description should be maximum 125 characters")
    String description;


    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "json should be maximum 125 characters")
    String json;


    @Column(length = 512)
    @NotNull
    @Size(max = 512, message = "template should be maximum 512 characters")
    String template;


    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "sqlStr should be maximum 125 characters")
    String sqlStr;


    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "sqlPart should be maximum 125 characters")
    String sqlPart;


}
