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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer id;


    @Column(length = 125)
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 125, message = "Name should be maximum 125 characters")
    String name;


    @Column(length = 125)
    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    @Size(max = 125, message = "Description should be maximum 125 characters")
    String description;


    @Column(length = 125)
    @NotNull(message = "JSON is mandatory")
    @NotBlank(message = "JSON is mandatory")
    @Size(max = 125, message = "JSON should be maximum 125 characters")
    String json;


    @Column(length = 512)
    @NotNull(message = "Template is mandatory")
    @NotBlank(message = "Template is mandatory")
    @Size(max = 512, message = "Template should be maximum 512 characters")
    String template;


    @Column(length = 125)
    @NotNull(message = "SQL Str is mandatory")
    @NotBlank(message = "SQL Str is mandatory")
    @Size(max = 125, message = "SQL Str should be maximum 125 characters")
    String sqlStr;


    @Column(length = 125)
    @NotNull(message = "SQL Part is mandatory")
    @NotBlank(message = "SQL Part is mandatory")
    @Size(max = 125, message = "SQL Part should be maximum 125 characters")
    String sqlPart;


}
