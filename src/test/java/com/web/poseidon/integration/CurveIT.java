package com.web.poseidon.integration;

import com.web.poseidon.domain.CurvePoint;
import com.web.poseidon.repositories.CurvePointRepository;
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
public class CurveIT {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    static Integer curveId = 1;

    @Test
    @WithUserDetails("test")
    void validateCurvePoint() throws Exception {

        //GIVEN
        List<CurvePoint> curvePointLists = curvePointRepository.findAll();

        // WHEN
        mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", String.valueOf(curveId)))
                .andDo(print())
                .andExpect(view().name("redirect:/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
        List<CurvePoint> curvePointAfterAdd = curvePointRepository.findAll();

        //THEN
        assertEquals(curvePointAfterAdd.size(), curvePointLists.size() + 1);
    }

}
