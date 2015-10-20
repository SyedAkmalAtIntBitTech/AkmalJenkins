/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.service.MarketingCategoryService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author intbit-6
 */
@Controller
public class MarketingCategoryController {
    private static final Logger logger = Logger.getLogger(MarketingCategoryController.class.getName());
    @Autowired
    private MarketingCategoryService marketingCategoryService;
    @RequestMapping("/dashboard")
	public ModelAndView getAllMarketingCategory() {
		ModelAndView mav = new ModelAndView("login");
		try {
                    System.out.println("hii");
			List<TblMarketingCategory> marketingCategorysList = marketingCategoryService.getAllMarketingCategory();
			mav.addObject("MARKETING_DETAILS_RESULTS_KEY", marketingCategorysList);
		}
		
		catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			mav.addObject("mainError", throwable.getMessage());
		}
		return mav;
    
}
}
