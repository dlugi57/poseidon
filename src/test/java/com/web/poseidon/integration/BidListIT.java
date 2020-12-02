package com.web.poseidon.integration;

import com.web.poseidon.domain.BidList;
import com.web.poseidon.repositories.BidListRepository;
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
public class BidListIT {

    @Autowired
    private BidListRepository bidListRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    static String account = "AccountTest";
    static String type = "TypeTest";
    static Double bidQuantity = 500d;

    @Test
    @WithUserDetails("test")
    void validateBidList() throws Exception {

        //GIVEN
        List<BidList> bidListsBeforeAdd = bidListRepository.findAll();

        // WHEN
        mockMvc.perform(post("/bidList/validate")
                .param("account", account)
                .param("type", type)
                .param("bidQuantity", String.valueOf(bidQuantity)))
                .andDo(print())
                .andExpect(view().name("redirect:/bidList/list"))
                .andExpect(status().is3xxRedirection());
        List<BidList> bidListsAfterAdd = bidListRepository.findAll();

        //THEN
        assertEquals(bidListsAfterAdd.size(), bidListsBeforeAdd.size() + 1);
    }

}
