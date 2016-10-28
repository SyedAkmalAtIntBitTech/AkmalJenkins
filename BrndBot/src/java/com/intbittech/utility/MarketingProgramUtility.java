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

        String marketingProgramCategory = IConstants.MARKETING_PROGRAM_CATEGORY + marketingProgramId;

        return marketingProgramCategory;

    }

    public static String getMarketingProgramActionCategory(Integer actionId) {

        String marketingProgramCategory = IConstants.MARKETING_PROGRAM__ACTION_CATEGORY + actionId;

        return marketingProgramCategory;

    }

    public static String getMarketingProgramRecuringActionCategory(Integer actionId) {

        String marketingProgramCategory = IConstants.MARKETING_PROGRAM__RECURING_ACTION_CATEGORY + actionId;

        return marketingProgramCategory;

    }
    
    public static String getDirectEmailCategory(String Id) {

        String category=  IConstants.DIRECT_EMAIL_CATEGORY + Id;

        return category;

    }
}
