package com.web.poseidon.integration;

import com.web.poseidon.domain.Rating;
import com.web.poseidon.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithUserDetails("test")
public class RatingIT {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    static String moodysRating = "testModys";
    static String sandPRating = "testPR";
    static String fitchRating = "testFitch";

    @Test
    @WithUserDetails("test")
    void validateRating() throws Exception {

        //GIVEN
        List<Rating> ratingListsBeforeAdd = ratingRepository.findAll();

        // WHEN
        mockMvc.perform(post("/rating/validate")
                .param("moodysRating", moodysRating)
                .param("sandPRating", sandPRating)
                .param("fitchRating", fitchRating))
                .andDo(print())
                .andExpect(view().name("redirect:/rating/list"))
                .andExpect(status().is3xxRedirection());
        List<Rating> ratingListsAfterAdd = ratingRepository.findAll();

        //THEN
        assertEquals(ratingListsAfterAdd.size(), ratingListsBeforeAdd.size() + 1);
    }

}
