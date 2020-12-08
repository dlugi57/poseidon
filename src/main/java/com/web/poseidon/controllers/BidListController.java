package com.web.poseidon.controllers;

import com.web.poseidon.domain.BidList;
import com.web.poseidon.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class BidListController {

    static final Logger logger = LogManager
            .getLogger(BidListController.class);

    // initialize objects
    BidListRepository bidListRepository;

    /**
     * Field injection of bid list dao
     *
     * @param bidListRepository bid list dao
     */
    @Autowired
    public BidListController(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * Get all bidList
     *
     * @param model model of view
     * @param principal get user info
     * @return List bidList
     */
    @RequestMapping("/bidList/list")
    public String home(Model model, Principal principal) {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        // call depository find all bids to show to the view
        model.addAttribute("bidList", bidListRepository.findAll());

        logger.info((principal.getName() + " is connected at "
                + format.format(calendar.getTime())));

        return "bidList/list";
    }

    /**
     * Add bidList form
     *
     * @param bid empty bid object
     * @return Empty form
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Add bid list
     *
     * @param bid    bid object
     * @param result when validation goes wrong result
     * @param model  model of view
     * @return when success list of bid if not add form
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            bidListRepository.save(bid);
            model.addAttribute("bidList", bidListRepository.findAll());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    /**
     * Update bid form
     *
     * @param id    id of bid to update
     * @param model model of view
     * @return form with bid to update
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get Bid by Id and to model then show to the form
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid bidList Id:" + id));

        model.addAttribute("bidList", bidList);

        return "bidList/update";
    }

    /**
     * Update bid
     *
     * @param id      id of bid to update
     * @param bidList bid object
     * @param result  when validation goes wrong result
     * @param model   model of view
     * @return when success list of bid if not update form
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        //  check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidList.setId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidList", bidListRepository.findAll());

        return "redirect:/bidList/list";
    }

    /**
     * Delete bid
     *
     * @param id    id of bid to delete
     * @param model model of view
     * @return list of bid when success
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // Find Bid by Id and delete the bid, return to Bid list
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidList", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }
}
