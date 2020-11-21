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
@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    Integer id;

    @Column(length = 125)
    @NotNull(message = "MoodysRating is mandatory")
    @NotBlank(message = "MoodysRating is mandatory")
    @Size(max = 125, message = "moodysRating should be maximum 125 characters")
    String moodysRating;

    @Column(length = 125)
    @NotNull(message = "SandPRating is mandatory")
    @NotBlank(message = "SandPRating is mandatory")
    @Size(max = 125, message = "sandPRating should be maximum 125 characters")
    String sandPRating;

    @Column(length = 125)
    @NotNull(message = "FitchRating is mandatory")
    @NotBlank(message = "FitchRating is mandatory")
    @Size(max = 125, message = "fitchRating should be maximum 125 characters")
    String fitchRating;

    @Column(columnDefinition = "TINYINT")
    Integer orderNumber;


}
