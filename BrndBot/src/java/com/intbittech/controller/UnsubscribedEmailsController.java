/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.services.UnsubscribedEmailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ajit @ Intbit
 */
@Controller
public class UnsubscribedEmailsController {
     private static Logger logger = Logger.getLogger(UnsubscribedEmailsController.class);
     
     @Autowired
     private UnsubscribedEmailsService unsubscribedEmailsService;
     
    
}
