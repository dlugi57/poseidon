package com.web.poseidon.controllers;

import com.web.poseidon.domain.Trade;
import com.web.poseidon.repositories.TradeRepository;
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
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Principal principal;

    @MockBean
    private TradeRepository tradeRepository;

    static String user = "test";
    static String account = "account";
    static String type = "type";
    static String incorrectType = null;

    @Test
    void home() throws Exception {
        List<Trade> tradeLists = new ArrayList<>();

        //GIVEN
        Trade trade = new Trade();
        trade.setId(1);
        trade.setType(type);
        trade.setAccount(account);

        tradeLists.add(trade);
        //WHEN
        when(principal.getName()).thenReturn(user);
        when(tradeRepository.findAll()).thenReturn(tradeLists);

        //THEN
        mockMvc.perform(get("/trade/list").principal(principal))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"));
    }

    @Test
    void addTradeForm() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/trade/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    void validate() throws Exception {
        List<Trade> tradeLists = new ArrayList<>();

        //GIVEN
        Trade trade = new Trade();
        trade.setId(1);
        trade.setType(type);
        trade.setAccount(account);

        tradeLists.add(trade);
        // WHEN
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));

        //THEN
        mockMvc.perform(post("/trade/validate")
                .param("account", account)
                .param("type", type))
                .andDo(print())
                .andExpect(view().name("redirect:/trade/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validate_invalid() throws Exception {
        //GIVEN
        List<Trade> tradeLists = new ArrayList<>();

        Trade trade = new Trade();
        trade.setId(1);
        trade.setType(type);
        trade.setAccount(account);

        tradeLists.add(trade);
        // WHEN
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));


        //WHEN //THEN return the update page
        mockMvc.perform(post("/trade/validate")
                .param("account", account)
                .param("type", incorrectType))
                .andDo(print())
                .andExpect(view().name("trade/add"))
                .andExpect(status().isOk());
    }

    @Test
    void showUpdateForm() throws Exception {
        //GIVEN
        Trade trade = new Trade();
        trade.setId(1);
        trade.setType(type);
        trade.setAccount(account);

        //WHEN
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));

        //THEN
        mockMvc.perform(get("/trade/update/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }

    @Test
    void updateTrade() throws Exception {
        //GIVEN
        Trade trade = new Trade();
        trade.setId(1);
        trade.setType(type);
        trade.setAccount(account);
        //WHEN
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));

        //THEN
        mockMvc.perform(post("/trade/update/1")
                .param("type", type)
                .param("account", account))
                .andDo(print())
                .andExpect(view().name("redirect:/trade/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateTrade_Invalid() throws Exception {
        //GIVEN
        Trade trade = new Trade();
        trade.setId(1);
        trade.setType(type);
        trade.setAccount(account);
        //WHEN
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));

        //THEN
        mockMvc.perform(post("/trade/update/1")
                .param("type", incorrectType)
                .param("account", account))
                .andDo(print())
                .andExpect(view().name("trade/update"))
                .andExpect(status().isOk());

    }

    @Test
    void deleteTrade() throws Exception {
        //GIVEN
        Trade trade = new Trade();
        trade.setId(1);
        trade.setType(type);
        trade.setAccount(account);

        //WHEN
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));

        //THEN
        mockMvc.perform(get("/trade/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));

        verify(tradeRepository, times(1)).delete(any(Trade.class));
    }
}