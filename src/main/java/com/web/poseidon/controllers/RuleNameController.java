package com.web.poseidon.controllers;

import com.web.poseidon.domain.RuleName;
import com.web.poseidon.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class RuleNameController {

    static final Logger logger = LogManager
            .getLogger(RuleNameController.class);

    // initialize objects
    RuleNameRepository ruleNameRepository;

    /**
     * Field injection of ruleName dao
     *
     * @param ruleNameRepository ruleName dao
     */
    @Autowired
    public RuleNameController(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * Get all ruleName
     *
     * @param model model of view
     * @param principal get user info
     * @return List ruleName
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model, Principal principal) {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        logger.info((principal.getName() + " is connected at "
                + format.format(calendar.getTime())));
        // find all RuleName, add to model
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        return "ruleName/list";
    }

    /**
     * Add ruleName form
     *
     * @param ruleName empty ruleName object
     * @return Empty form
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    /**
     * Add ruleName
     *
     * @param ruleName ruleName object
     * @param result   when validation goes wrong result
     * @param model    model of view
     * @return when success list of ruleName if not add form
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameRepository.save(ruleName);
            model.addAttribute("ruleNames", ruleNameRepository.findAll());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    /**
     * Update ruleName form
     *
     * @param id    id of ruleName to update
     * @param model model of view
     * @return form with ruleName to update
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get RuleName by Id and to model then show to the form
        RuleName ruleName =
                ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Invalid ruleName Id:" + id));

        model.addAttribute("ruleName", ruleName);

        return "ruleName/update";
    }

    /**
     * Update ruleName
     *
     * @param id       id of rating to update
     * @param ruleName ruleName object
     * @param result   when validation goes wrong result
     * @param model    model of view
     * @return when success list of ruleName if not update form
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        // check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());

        return "redirect:/ruleName/list";
    }

    /**
     * Delete ruleName
     *
     * @param id    id of ruleName to delete
     * @param model model of view
     * @return list of ruleName when success
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // Find RuleName by Id and delete the RuleName, return to Rule list
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid ruleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        model.addAttribute("ratings", ruleNameRepository.findAll());
        return "redirect:/ruleName/list";
    }
}
