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
    public static final String BASE_IMAGE_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "Images";
    public static final String BASE_IMAGE_COMPANY = BASE_IMAGE_UPLOAD_PATH + File.separator + "companies";
}
