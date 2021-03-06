/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import com.google.gson.Gson;
import java.io.File;

/**
 *
 * @author Mohamed
 */
public class AppConstants {
    
    public static final Gson GSON = new Gson();

    public static final String BASE_UPLOAD_PATH = "/home/ilyas/tomcat/BrndBot";
    
    public static final String BASE_IMAGE_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "Images";
    
    public static final String BASE_FONT_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "fonts";
    
    public static final String BASE_XML_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "xml";

    public static final String BASE_HTML_TEMPLATE_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "HTMLTemplates";
    
    public static final String BASE_TEMP_PATH = BASE_UPLOAD_PATH + File.separator + "TEMP";

    public static final String BASE_MODEL_PATH = BASE_UPLOAD_PATH + File.separator + "model";
            
    public static final String USER_IMAGE_HOME = BASE_IMAGE_UPLOAD_PATH + File.separator + "Gallery";
    
    public static final String ADMIN_IMAGE_HOME = BASE_IMAGE_UPLOAD_PATH + File.separator + "admin";
    
    public static final String LOOK_IMAGES_HOME = ADMIN_IMAGE_HOME + File.separator + "LookImages";
    
    public static final String BRAND_IMAGES_HOME = ADMIN_IMAGE_HOME + File.separator + "BrandImages";
    
    public static final String ORG_CATEGORIES_HOME = ADMIN_IMAGE_HOME + File.separator + "Organizations" 
            + File.separator + "Categories";
    
    public static final String LAYOUT_IMAGES_HOME = ADMIN_IMAGE_HOME + File.separator + "LayoutImages";
    
    public static final String LAYOUT_HTML_HOME = BASE_UPLOAD_PATH + File.separator + "LayoutHTML";
    
    public static final String ADMIN_LAYOUT_BACKGROUNDIMAGES_HOME = ADMIN_IMAGE_HOME + File.separator + "AdminLayoutBackgroundImages";
    
    public static final String ADMIN_LAYOUT_SVG_HOME = ADMIN_IMAGE_HOME + File.separator + "AdminLayoutSVG";
    
    public static final String TMP_FOLDER = "/tmp";
    
    public static final String USER_LOGO = BASE_IMAGE_UPLOAD_PATH + File.separator + "Customers";
    
}
