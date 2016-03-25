/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import org.springframework.web.bind.annotation.RestController;
import com.intbittech.services.RecuringEmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

        


/**
 *
 * @author sandeep-kumar
 */

@RestController
public class RecuringEmailController 
{
    @Autowired
    private RecuringEmailTemplateService recuringEmailTemplateService;
     
    
    
}
