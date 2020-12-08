package com.web.poseidon.controllers;

import com.web.poseidon.domain.BidList;
import com.web.poseidon.repositories.BidListRepository;
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
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Principal principal;

    @MockBean
    private BidListRepository bidListRepository;

    static String user = "test";
    static String account = "AccountTest";
    static String type = "TypeTest";
    static Double bidQuantity = 500d;
    static Double incorrectBidQuantity = null;

    @Test
    void home() throws Exception {

        List<BidList> bidLists = new ArrayList<>();

        //GIVEN
        BidList bidListTest = new BidList();
        bidListTest.setId(1);
        bidListTest.setAccount(account);
        bidListTest.setType(type);
        bidListTest.setBidQuantity(bidQuantity);

        bidLists.add(bidListTest);
        //WHEN
        when(principal.getName()).thenReturn(user);
        when(bidListRepository.findAll()).thenReturn(bidLists);

        //THEN
        mockMvc.perform(get("/bidList/list").principal(principal))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));

    }

    @Test
    void addBidForm() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/bidList/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void validate() throws Exception {
        //GIVEN
        BidList bidListTest = new BidList();

        bidListTest.setId(1);
        bidListTest.setAccount(account);
        bidListTest.setType(type);
        bidListTest.setBidQuantity(bidQuantity);
        // WHEN
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidListTest);
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidListTest));

        //THEN
        mockMvc.perform(post("/bidList/validate")
                .param("account", account)
                .param("type", type)
                .param("bidQuantity", String.valueOf(bidQuantity)))
                .andDo(print())
                .andExpect(view().name("redirect:/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validate_invalid() throws Exception {
        //GIVEN
        BidList bidListTest = new BidList();

        bidListTest.setId(1);
        bidListTest.setAccount(account);
        bidListTest.setType(type);
        bidListTest.setBidQuantity(bidQuantity);
        // WHEN
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidListTest);
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidListTest));


        //WHEN //THEN return the update page
        mockMvc.perform(post("/bidList/validate")
                .param("account", account)
                .param("type", type)
                .param("bidQuantity", String.valueOf(incorrectBidQuantity)))
                .andDo(print())
                .andExpect(view().name("bidList/add"))
                .andExpect(status().isOk());
    }

    @Test
    void showUpdateForm() throws Exception {
        //GIVEN
        BidList bidListTest = new BidList();
        bidListTest.setId(1);
        bidListTest.setAccount(account);
        bidListTest.setType(type);
        bidListTest.setBidQuantity(bidQuantity);
        //WHEN
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidListTest));

        //THEN
        mockMvc.perform(get("/bidList/update/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void updateBid() throws Exception {
        //GIVEN
        BidList bidListTest = new BidList();
        bidListTest.setId(1);
        bidListTest.setAccount(account);
        bidListTest.setType(type);
        bidListTest.setBidQuantity(bidQuantity);
        //WHEN
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidListTest));

        //THEN
        mockMvc.perform(post("/bidList/update/1")
                .param("account", account)
                .param("type", type)
                .param("bidQuantity", String.valueOf(bidQuantity)))
                .andDo(print())
                .andExpect(view().name("redirect:/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateBid_InvalidBid() throws Exception {
        //GIVEN
        BidList bidListTest = new BidList();
        bidListTest.setId(1);
        bidListTest.setAccount(account);
        bidListTest.setType(type);
        bidListTest.setBidQuantity(bidQuantity);
        //WHEN
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidListTest));

        //THEN
        mockMvc.perform(post("/bidList/update/1")
                .param("account", account)
                .param("type", type)
                .param("bidQuantity", String.valueOf(incorrectBidQuantity)))
                .andDo(print())
                .andExpect(view().name("bidList/update"))
                .andExpect(status().isOk());

    }

    @Test
    void deleteBid() throws Exception {
        //GIVEN
        BidList bidListTest = new BidList();
        bidListTest.setId(1);
        bidListTest.setAccount(account);
        bidListTest.setType(type);
        bidListTest.setBidQuantity(bidQuantity);

        //WHEN
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidListTest));

        //THEN
        mockMvc.perform(get("/bidList/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));

        verify(bidListRepository, times(1)).delete(any(BidList.class));
    }
}