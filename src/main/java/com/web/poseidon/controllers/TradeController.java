package com.web.poseidon.controllers;

import com.web.poseidon.domain.RuleName;
import com.web.poseidon.domain.Trade;
import com.web.poseidon.repositories.RuleNameRepository;
import com.web.poseidon.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TradeController {

    // initialize objects
    TradeRepository tradeRepository;

    /**
     * Field injection of trade dao
     *
     * @param tradeRepository trade dao
     */
    @Autowired
    public TradeController(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Get all trade
     *
     * @param model model of view
     * @return List trade
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // find all Trade, add to model
        model.addAttribute("trades", tradeRepository.findAll());
        return "trade/list";
    }

    /**
     * Add trade form
     *
     * @param trade empty trade object
     * @return Empty form
     */
    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        return "trade/add";
    }

    /**
     * Add trade
     *
     * @param trade trade object
     * @param result when validation goes wrong result
     * @param model  model of view
     * @return when success list of trade if not add form
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Trade list
        if (!result.hasErrors()) {
            tradeRepository.save(trade);
            model.addAttribute("trades", tradeRepository.findAll());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    /**
     * Update trade form
     *
     * @param id    id of trade to update
     * @param model model of view
     * @return form with trade to update
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //  get Trade by Id and to model then show to the form
        Trade trade =
                tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Invalid trade Id:" + id));

        model.addAttribute("trade", trade);

        return "trade/update";
    }


    /**
     * Update trade
     *
     * @param id     id of trade to update
     * @param trade trade object
     * @param result when validation goes wrong result
     * @param model  model of view
     * @return when success list of trade if not update form
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            return "trade/update";
        }

        trade.setId(id);
        tradeRepository.save(trade);
        model.addAttribute("trades", tradeRepository.findAll());

        return "redirect:/trade/list";
    }

    /**
     * Delete trade
     *
     * @param id    id of trade to delete
     * @param model model of view
     * @return list of trade when success
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // Find Trade by Id and delete the Trade, return to Trade list
        Trade trade =
                tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        return "redirect:/trade/list";
    }
}
