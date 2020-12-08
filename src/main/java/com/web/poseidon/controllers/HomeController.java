package com.web.poseidon.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class HomeController {

    static final Logger logger = LogManager
            .getLogger(HomeController.class);

    /**
     * Home page
     * @param model model of view
     * @param principal get user info
     * @return home page
     */
    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        logger.info((principal.getName() + " is connected at "
                + format.format(calendar.getTime())));
        return "home";
    }

    /**
     * Home page
     * @param model model of view
     * @param principal get user info
     * @return admin home page
     */
    @RequestMapping("/admin/home")
    public String adminHome(Model model, Principal principal) {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        logger.info((principal.getName() + " is connected at "
                + format.format(calendar.getTime())));

        return "redirect:/bidList/list";
    }

}
