package com.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", "Неправильно введені ім'я чи пароль!");
        }

        if (logout != null) {
            model.addObject("logout", "Вихід підтреджено!");
        }

        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "")
    public String homePage() {
        return "redirect:/shop";
    }
}
