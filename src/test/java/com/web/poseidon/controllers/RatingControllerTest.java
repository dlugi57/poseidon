package com.web.poseidon.controllers;

import com.web.poseidon.domain.Rating;
import com.web.poseidon.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Principal principal;

    @MockBean
    private RatingRepository ratingRepository;

    static String user = "test";
    static String moodysRating = "testModys";
    static String sandPRating = "testPR";
    static String fitchRating = "testFitch";
    static String incorrectFitchRating = null;

    @Test
    void home() throws Exception {
        List<Rating> ratingLists = new ArrayList<>();

        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating(moodysRating);
        rating.setSandPRating(sandPRating);
        rating.setFitchRating(fitchRating);

        ratingLists.add(rating);
        //WHEN
        when(principal.getName()).thenReturn(user);
        when(ratingRepository.findAll()).thenReturn(ratingLists);

        //THEN
        mockMvc.perform(get("/rating/list").principal(principal))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"));
    }

    @Test
    void addRatingForm() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/rating/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    void validate() throws Exception {
        List<Rating> ratingLists = new ArrayList<>();

        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating(moodysRating);
        rating.setSandPRating(sandPRating);
        rating.setFitchRating(fitchRating);

        ratingLists.add(rating);
        // WHEN
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        //THEN
        mockMvc.perform(post("/rating/validate")
                .param("moodysRating", moodysRating)
                .param("sandPRating", sandPRating)
                .param("fitchRating", fitchRating))
                .andDo(print())
                .andExpect(view().name("redirect:/rating/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validate_invalid() throws Exception {
        //GIVEN
        List<Rating> ratingLists = new ArrayList<>();

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating(moodysRating);
        rating.setSandPRating(sandPRating);
        rating.setFitchRating(fitchRating);

        ratingLists.add(rating);
        // WHEN
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));


        //WHEN //THEN return the update page
        mockMvc.perform(post("/rating/validate")
                .param("moodysRating", moodysRating)
                .param("sandPRating", sandPRating)
                .param("fitchRating", incorrectFitchRating))
                .andDo(print())
                .andExpect(view().name("rating/add"))
                .andExpect(status().isOk());
    }

    @Test
    void showUpdateForm() throws Exception {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating(moodysRating);
        rating.setSandPRating(sandPRating);
        rating.setFitchRating(fitchRating);

        //WHEN
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        //THEN
        mockMvc.perform(get("/rating/update/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    @Test
    void updateRating() throws Exception {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating(moodysRating);
        rating.setSandPRating(sandPRating);
        rating.setFitchRating(fitchRating);
        //WHEN
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        //THEN
        mockMvc.perform(post("/rating/update/1")
                .param("moodysRating", moodysRating)
                .param("sandPRating", sandPRating)
                .param("fitchRating", fitchRating))
                .andDo(print())
                .andExpect(view().name("redirect:/rating/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateRating_Invalid() throws Exception {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating(moodysRating);
        rating.setSandPRating(sandPRating);
        rating.setFitchRating(fitchRating);
        //WHEN
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        //THEN
        mockMvc.perform(post("/rating/update/1")
                .param("moodysRating", moodysRating)
                .param("sandPRating", sandPRating)
                .param("fitchRating", incorrectFitchRating))
                .andDo(print())
                .andExpect(view().name("rating/update"))
                .andExpect(status().isOk());

    }

    @Test
    void deleteRating() throws Exception {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating(moodysRating);
        rating.setSandPRating(sandPRating);
        rating.setFitchRating(fitchRating);

        //WHEN
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        //THEN
        mockMvc.perform(get("/rating/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));

        verify(ratingRepository, times(1)).delete(any(Rating.class));
    }
}