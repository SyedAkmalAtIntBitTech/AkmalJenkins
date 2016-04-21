/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.exception.ResourceNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
public class BrndBotHttpErrorHandlerController {
         
   
    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String erorr404Page(ModelMap model) {
        return "error404";
    } 
    
}
