package com.web.poseidon.controllers;

import com.web.poseidon.domain.User;
import com.web.poseidon.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserController {

    static final Logger logger = LogManager
            .getLogger(UserController.class);

    // initilize repository
    @Autowired
    private UserRepository userRepository;

    /**
     * Users list
     *
     * @param model     model of view
     * @param principal get user info
     * @return home page
     */
    @RequestMapping("/user/list")
    public String home(Model model, Principal principal) {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        logger.info((principal.getName() + " is connected at "
                + format.format(calendar.getTime())));
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**
     * User add page
     *
     * @param user user object
     * @return empty form
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    /**
     * Add user
     *
     * @param user   user object
     * @param result when validation goes wrong result
     * @param model  model of view
     * @return when success list of bid if not add form
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Update user form
     * @param id user id to update
     * @param model model of view
     * @return form with user to update
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Update user
     * @param id user id
     * @param user user object
     * @param result when validation goes wrong result
     * @param model model of view
     * @return when success list of bid if not update form
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,

                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    /**
     * Delete user
     * @param id user id
     * @param model moedl of view
     * @return list of users when success
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
