/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech;

import com.google.gson.Gson;
import java.io.File;

/**
 *
 * @author Haider Khan @ Intbit
 */
public class AppConstants {

    public static final Gson GSON = new Gson();

    public static final String BASE_UPLOAD_PATH = "/home/tomcat/BrndBot";
    public static final String BASE_ADMIN_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "admin";
    public static final String BASE_IMAGE_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "images";
    public static final String BASE_IMAGE_COMPANY = BASE_IMAGE_UPLOAD_PATH + File.separator + "companies";
    public static final String twitterString1 = "K7TJ3va8cyAeh6oN3Hia91S2o";
    public static final String twitterString2 = "IWUt2aDVTHgUc8N0qI0cF1Z1dTAEQ7CSgnBymZNr3BPSmtkNHL";
    public static final String facebookString1 = "592852577521569";
    public static final String facebookString2 = "a87cc0c30d792fa5dd0aaef6b43994ef";
    public static final String facebookPermissions = "publish_actions, publish_pages,manage_pages";
}
