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
@RequestMapping(value = "/signup")
public class SignupController {

    @RequestMapping(value = "/{jspFileName}", method = RequestMethod.GET)
    public String signUpJspPages(ModelMap model,@PathVariable(value = "jspFileName") String jspFileName) {
        return "signup/" + jspFileName;
    }
}
