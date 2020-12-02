package com.web.poseidon.controllers;

import com.web.poseidon.domain.CurvePoint;
import com.web.poseidon.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
class CurveControllerTest {

    static Integer curveId = 1;
    static Integer incorrectCurveId = null;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointRepository curvePointRepository;

    @Test
    void home() throws Exception {
        List<CurvePoint> curvePointLists = new ArrayList<>();

        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);

        curvePointLists.add(curvePoint);
        //WHEN
        when(curvePointRepository.findAll()).thenReturn(curvePointLists);

        //THEN
        mockMvc.perform(get("/curvePoint/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"));

    }

    @Test
    void addCurvePointForm() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/curvePoint/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validate() throws Exception {
        List<CurvePoint> curvePointLists = new ArrayList<>();

        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);

        curvePointLists.add(curvePoint);
        // WHEN
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        //THEN
        mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", String.valueOf(curveId)))
                .andDo(print())
                .andExpect(view().name("redirect:/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validate_invalid() throws Exception {
        //GIVEN
        List<CurvePoint> curvePointLists = new ArrayList<>();

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);

        curvePointLists.add(curvePoint);
        // WHEN
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));


        //WHEN //THEN
        mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", String.valueOf(incorrectCurveId)))
                .andDo(print())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(status().isOk());
    }

    @Test
    void showUpdateForm() throws Exception {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);

        //WHEN
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        //THEN
        mockMvc.perform(get("/curvePoint/update/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void updateCurvePoint() throws Exception {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        //WHEN
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        //THEN
        mockMvc.perform(post("/curvePoint/update/1")
                .param("curveId", String.valueOf(curveId)))
                .andDo(print())
                .andExpect(view().name("redirect:/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateCurvePoint_InvalidBid() throws Exception {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        //WHEN
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        //THEN
        mockMvc.perform(post("/curvePoint/update/1")
                .param("curveId", String.valueOf(incorrectCurveId)))
                .andDo(print())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCurvePoint() throws Exception {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);

        //WHEN
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        //THEN
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));

        verify(curvePointRepository, times(1)).delete(any(CurvePoint.class));
    }
}