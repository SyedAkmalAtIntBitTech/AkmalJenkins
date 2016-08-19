/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.UserProfile;
import com.intbittech.utility.UserSessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Haider Khan @ Intbit
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    
     @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminWelcomePage(ModelMap model) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile);
        return "admin/organization";
    }
    
    @RequestMapping(value = "/{htmlFileName}", method = RequestMethod.GET)
    public String adminJspPages(ModelMap model,@PathVariable(value = "htmlFileName") String htmlFileName) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile);
        return "admin/" + htmlFileName;
    }
    

}
