/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author development
 */

//TODO remove this is unused
@Controller
public class MarketingEmailAutomationController {
    String return_response = "false";
    
    public @ResponseBody String getRecurringEmailTemplates(){
        return return_response;
    }
    
}
