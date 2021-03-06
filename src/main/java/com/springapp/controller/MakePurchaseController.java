package com.springapp.controller;


import com.springapp.anotation.ActiveUser;
import com.springapp.exceptions.ItemNotAvailableException;
import com.springapp.model.Address;
import com.springapp.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MakePurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "makePurchase", method = RequestMethod.POST)
    public ModelAndView makeOrder(@ModelAttribute("address") @Valid Address address, BindingResult result,
                                  @ActiveUser User activeUser) {
        if(result.hasErrors()) {

            return new ModelAndView("redirect:/cart#error");
        }

        try {
            purchaseService.makePurchase(address, activeUser.getUsername());
            return new ModelAndView("cart", "success", "");
        } catch (ItemNotAvailableException e) {
            return new ModelAndView("redirect:/cart");
        }
    }


}
