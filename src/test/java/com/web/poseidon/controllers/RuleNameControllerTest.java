package com.web.poseidon.controllers;

import com.web.poseidon.domain.RuleName;
import com.web.poseidon.repositories.RuleNameRepository;
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
class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameRepository ruleNameRepository;

    static String name = "name";
    static String description = "description";
    static String json = "json";
    static String template = "template";
    static String sqlStr = "sqlStr";
    static String sqlPart = "sqlPart";
    static String incorrectSqlPart = null;


    @Test
    void home() throws Exception {
        List<RuleName> ruleNameLists = new ArrayList<>();

        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName(name);
        ruleName.setDescription(description);
        ruleName.setTemplate(template);
        ruleName.setJson(json);
        ruleName.setSqlStr(sqlStr);
        ruleName.setSqlPart(sqlPart);

        ruleNameLists.add(ruleName);
        //WHEN
        when(ruleNameRepository.findAll()).thenReturn(ruleNameLists);

        //THEN
        mockMvc.perform(get("/ruleName/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    void addRuleNameForm() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/ruleName/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validate() throws Exception {
        List<RuleName> ruleNameLists = new ArrayList<>();

        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName(name);
        ruleName.setDescription(description);
        ruleName.setTemplate(template);
        ruleName.setJson(json);
        ruleName.setSqlStr(sqlStr);
        ruleName.setSqlPart(sqlPart);

        ruleNameLists.add(ruleName);
        // WHEN
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        //THEN
        mockMvc.perform(post("/ruleName/validate")
                .param("name", name)
                .param("description", description)
                .param("json", json)
                .param("template", template)
                .param("sqlStr", sqlStr)
                .param("sqlPart", sqlPart))
                .andDo(print())
                .andExpect(view().name("redirect:/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validate_invalid() throws Exception {
        //GIVEN
        List<RuleName> ruleNameLists = new ArrayList<>();

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName(name);
        ruleName.setDescription(description);
        ruleName.setTemplate(template);
        ruleName.setJson(json);
        ruleName.setSqlStr(sqlStr);
        ruleName.setSqlPart(sqlPart);

        ruleNameLists.add(ruleName);
        // WHEN
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));


        //WHEN //THEN return the update page
        mockMvc.perform(post("/ruleName/validate")
                .param("name", name)
                .param("description", description)
                .param("json", json)
                .param("template", template)
                .param("sqlStr", sqlStr)
                .param("sqlPart", incorrectSqlPart))
                .andDo(print())
                .andExpect(view().name("ruleName/add"))
                .andExpect(status().isOk());
    }

    @Test
    void showUpdateForm() throws Exception {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName(name);
        ruleName.setDescription(description);
        ruleName.setTemplate(template);
        ruleName.setJson(json);
        ruleName.setSqlStr(sqlStr);
        ruleName.setSqlPart(sqlPart);

        //WHEN
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        //THEN
        mockMvc.perform(get("/ruleName/update/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void updateRuleName() throws Exception {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName(name);
        ruleName.setDescription(description);
        ruleName.setTemplate(template);
        ruleName.setJson(json);
        ruleName.setSqlStr(sqlStr);
        ruleName.setSqlPart(sqlPart);
        //WHEN
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        //THEN
        mockMvc.perform(post("/ruleName/update/1")
                .param("name", name)
                .param("description", description)
                .param("json", json)
                .param("template", template)
                .param("sqlStr", sqlStr)
                .param("sqlPart", sqlPart))
                .andDo(print())
                .andExpect(view().name("redirect:/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateRuleName_InvalidBid() throws Exception {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName(name);
        ruleName.setDescription(description);
        ruleName.setTemplate(template);
        ruleName.setJson(json);
        ruleName.setSqlStr(sqlStr);
        ruleName.setSqlPart(sqlPart);
        //WHEN
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        //THEN
        mockMvc.perform(post("/ruleName/update/1")
                .param("name", name)
                .param("description", description)
                .param("json", json)
                .param("template", template)
                .param("sqlStr", sqlStr)
                .param("sqlPart", incorrectSqlPart))
                .andDo(print())
                .andExpect(view().name("ruleName/update"))
                .andExpect(status().isOk());

    }

    @Test
    void deleteRuleName() throws Exception {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName(name);
        ruleName.setDescription(description);
        ruleName.setTemplate(template);
        ruleName.setJson(json);
        ruleName.setSqlStr(sqlStr);
        ruleName.setSqlPart(sqlPart);

        //WHEN
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        //THEN
        mockMvc.perform(get("/ruleName/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));

        verify(ruleNameRepository, times(1)).delete(any(RuleName.class));
    }
}