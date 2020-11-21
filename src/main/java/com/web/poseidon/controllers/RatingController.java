package com.web.poseidon.controllers;

import com.web.poseidon.domain.Rating;
import com.web.poseidon.repositories.RatingRepository;
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
public class RatingController {

    // initialize objects
    RatingRepository ratingRepository;

    /**
     * Field injection of ratingdao
     *
     * @param ratingRepository rating dao
     */
    @Autowired
    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Get all rating
     *
     * @param model model of view
     * @return List rating
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        // find all Rating, add to model
        model.addAttribute("ratings", ratingRepository.findAll());
        return "rating/list";
    }

    /**
     * Add rating form
     *
     * @param rating empty rating object
     * @return Empty form
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Add rating
     *
     * @param rating rating object
     * @param result when validation goes wrong result
     * @param model  model of view
     * @return when success list of rating if not add form
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Rating list
        if (!result.hasErrors()) {
            ratingRepository.save(rating);
            model.addAttribute("ratings", ratingRepository.findAll());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    /**
     * Update rating form
     *
     * @param id    id of rating to update
     * @param model model of view
     * @return form with rating to update
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get Rating by Id and to model then show to the form
        Rating rating =
                ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Invalid rating Id:" + id));

        model.addAttribute("rating", rating);

        return "rating/update";
    }

    /**
     * Update rating
     *
     * @param id     id of rating to update
     * @param rating rating object
     * @param result when validation goes wrong result
     * @param model  model of view
     * @return when success list of rating if not update form
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        // check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            return "rating/update";
        }

        rating.setId(id);
        ratingRepository.save(rating);
        model.addAttribute("ratings", ratingRepository.findAll());

        return "redirect:/rating/list";
    }

    /**
     * Delete rating
     *
     * @param id    id of rating to delete
     * @param model model of view
     * @return list of rating when success
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // Find Rating by Id and delete the Rating, return to Rating list
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }
}
