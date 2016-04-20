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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rasim parvez
 */
@Controller
public class MappingController {
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String admisignupPage(ModelMap model) {              
        return "signup/signup";
    }
    @RequestMapping(value = "/signup/onboarding2", method = RequestMethod.GET)
    public String admisignup1nPage(ModelMap model) {              
        return "signup/onboarding2";
    }
    
//    @RequestMapping(value = "/signup/onboarding1", method = RequestMethod.GET)
//    public String admisignup1nPage(ModelMap model,@RequestParam("companyId") Integer companyId) {
//        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
//        model.addAttribute("user", userProfile.getUsername());
//        return "/signup/onboarding1?companyId="+companyId;
//    }
}
