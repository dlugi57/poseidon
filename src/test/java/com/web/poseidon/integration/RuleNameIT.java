package com.web.poseidon.integration;

import com.web.poseidon.domain.RuleName;
import com.web.poseidon.repositories.RuleNameRepository;
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
public class RuleNameIT {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    static String name = "name";
    static String description = "description";
    static String json = "json";
    static String template = "template";
    static String sqlStr = "sqlStr";
    static String sqlPart = "sqlPart";

    @Test
    @WithUserDetails("test")
    void validateRating() throws Exception {

        //GIVEN
        List<RuleName> ruleNameListsBeforeAdd = ruleNameRepository.findAll();

        // WHEN
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
        List<RuleName> ruleNameListsAfterAdd = ruleNameRepository.findAll();

        //THEN
        assertEquals(ruleNameListsAfterAdd.size(), ruleNameListsBeforeAdd.size() + 1);
    }

}
