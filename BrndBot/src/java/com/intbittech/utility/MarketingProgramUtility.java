/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

/**
 *
 * @author ajit @ Intbit
 */
public class MarketingProgramUtility {
    
    
    public static String getMarketingProgramCategory(Integer marketingProgramId) {

        if (marketingProgramId > 0){
           
            String marketingProgramCategory = IConstants.MARKETING_PROGRAM_CATEGORY + marketingProgramId;
           
            return  marketingProgramCategory;
        }
        return null;
    }    
    
     public static String getMarketingProgramActionCategory(Integer marketingProgramId) {

        if (marketingProgramId > 0){
           
            String marketingProgramCategory = IConstants.MARKETING_PROGRAM__ACTION_CATEGORY + marketingProgramId;
           
            return  marketingProgramCategory;
        }
        return null;
    }    
     
      public static String getMarketingProgramRecuringActionCategory(Integer marketingProgramId) {

        if (marketingProgramId > 0){
           
            String marketingProgramCategory = IConstants.MARKETING_PROGRAM__RECURING_ACTION_CATEGORY + marketingProgramId;
           
            return  marketingProgramCategory;
        }
        return null;
    }    
}
