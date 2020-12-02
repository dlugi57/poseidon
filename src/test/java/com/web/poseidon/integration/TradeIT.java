package com.web.poseidon.integration;

import com.web.poseidon.domain.BidList;
import com.web.poseidon.domain.Trade;
import com.web.poseidon.repositories.BidListRepository;
import com.web.poseidon.repositories.TradeRepository;
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
public class TradeIT {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    static String account = "account";
    static String type = "type";

    @Test
    @WithUserDetails("test")
    void validateBidList() throws Exception {

        //GIVEN
        List<Trade> tradeBeforeAdd = tradeRepository.findAll();

        // WHEN
        mockMvc.perform(post("/trade/validate")
                .param("account", account)
                .param("type", type))
                .andDo(print())
                .andExpect(view().name("redirect:/trade/list"))
                .andExpect(status().is3xxRedirection());
        List<Trade> tradeAfterAdd = tradeRepository.findAll();

        //THEN
        assertEquals(tradeAfterAdd.size(), tradeBeforeAdd.size() + 1);
    }

}
