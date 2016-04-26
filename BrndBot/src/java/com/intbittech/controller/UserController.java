/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.UserProfile;
import com.intbittech.utility.UserSessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String userWelcomePage(ModelMap model) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile.getUsername());
        return "user/dashboard";
    }
    
    @RequestMapping(value = "/{jspFileName}", method = RequestMethod.GET)
    public String UserJspPages(ModelMap model,@PathVariable(value = "jspFileName") String jspFileName) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile);
        return "user/" + jspFileName;
    }
    
}
