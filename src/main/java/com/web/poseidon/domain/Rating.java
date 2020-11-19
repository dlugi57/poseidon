package com.web.poseidon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", columnDefinition = "TINYINT")
    Integer id;

    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "moodysRating should be maximum 125 characters")
    String moodysRating;

    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "sandPRating should be maximum 125 characters")
    String sandPRating;

    @Column(length = 125)
    @NotNull
    @Size(max = 125, message = "fitchRating should be maximum 125 characters")
    String fitchRating;

    @Column(columnDefinition = "TINYINT")
    Integer orderNumber;


}
