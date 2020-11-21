package com.web.poseidon.controllers;

import com.web.poseidon.domain.CurvePoint;
import com.web.poseidon.repositories.CurvePointRepository;
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
public class CurveController {

    // initialize objects
    CurvePointRepository curvePointRepository;

    /**
     * Field injection of curve point dao
     *
     * @param curvePointRepository curve point dao
     */
    @Autowired
    public CurveController(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * Get all curvePoint
     *
     * @param model model of view
     * @return List curvePoint
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        // find all Curve Point, add to model
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        return "curvePoint/list";
    }

    /**
     * Add curvePoint form
     *
     * @param bid empty curvePoint object
     * @return Empty form
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    /**
     * Add curvePoint
     *
     * @param curvePoint curvePoint object
     * @param result     when validation goes wrong result
     * @param model      model of view
     * @return when success list of curvePoint if not add form
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            curvePointRepository.save(curvePoint);
            model.addAttribute("curvePoints", curvePointRepository.findAll());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    /**
     * Update curvePoint form
     *
     * @param id    id of curvePoint to update
     * @param model model of view
     * @return form with curvePoint to update
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint =
                curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Invalid curvePoint Id:" + id));

        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }

    /**
     * Update curvePoint
     *
     * @param id         id of curvePoint to update
     * @param curvePoint curvePoint object
     * @param result     when validation goes wrong result
     * @param model      model of view
     * @return when success list of curvePoint if not update form
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        // check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            return "curvePoint/update";
        }

        curvePoint.setId(id);
        curvePointRepository.save(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());

        return "redirect:/curvePoint/list";
    }

    /**
     * Delete curvePoint
     *
     * @param id    id of curvePoint to delete
     * @param model model of view
     * @return list of curvePoint when success
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // Find Curve by Id and delete the Curve, return to Curve list
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid curvePoint Id:" + id));
        curvePointRepository.delete(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        return "redirect:/curvePoint/list";
    }
}
