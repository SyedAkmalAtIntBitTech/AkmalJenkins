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
    
    public static final String COMPANY_LOGO_FILENAME = "companylogo.png";
    public static final String GALLERY_FOLDERNAME = "gallery";
    public static final String LOGO_FOLDERNAME = "logo";
    public static final String BASE_UPLOAD_PATH = "/home/tomcat/BrndBot";
    public static final String BASE_ADMIN_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "admin";
    public static final String BASE_USERS_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "users";
    public static final String BASE_USERS_IMAGE_UPLOAD_PATH = BASE_USERS_UPLOAD_PATH + File.separator + "images";
    public static final String BASE_ADMIN_IMAGE_UPLOAD_PATH = BASE_ADMIN_UPLOAD_PATH + File.separator + "images";
    public static final String BASE_ADMIN_FONT_UPLOAD_PATH = BASE_ADMIN_UPLOAD_PATH + File.separator + "fonts";
    public static final String BASE_IMAGE_COMPANY_UPLOAD_PATH = BASE_USERS_IMAGE_UPLOAD_PATH + File.separator + "companies";
    public static final String BASE_ADMIN_GLOBAL_IMAGE_UPLOAD_PATH = BASE_ADMIN_IMAGE_UPLOAD_PATH + File.separator + "globalimages";
    public static final String BASE_ADMIN_EMAIL_TEMPLATE_IMAGE_UPLOAD_PATH = BASE_ADMIN_IMAGE_UPLOAD_PATH + File.separator + "emailtemplates";
    public static final String BASE_HTML_TEMPLATE_UPLOAD_PATH = BASE_USERS_UPLOAD_PATH + File.separator + "HTMLTemplates";
    public static final Integer Datedifference = 172800000;
    public static final Integer UserRoleManagerValue = 2;
    public static final Integer UserRoleTempManagerValue = 3;
    public static final Integer UserRoleAccountOwnerValue = 5;
    public static final String SignUpStatusIncomplete = "Incomplete";
    public static final String SignUpStatuscomplete = "complete";
    public static final String Invite_Sent = "Invite Sent";
    public static final String Invite_Accepted = "Invite Accepted";
    public static final String Account_Activated = "Activated";
    public static final String Account_Deactivated = "Deactivated";
    public static final String User_Status_New = "New User";
    public static final String User_Status_Existing = "Existing User";
    
    public static final String BASE_IMAGE_UPLOAD_PATH = BASE_UPLOAD_PATH + File.separator + "images";
    public static final String BASE_IMAGE_COMPANY = BASE_IMAGE_UPLOAD_PATH + File.separator + "companies";
    public static final String twitterString1 = "K7TJ3va8cyAeh6oN3Hia91S2o";
    public static final String twitterString2 = "IWUt2aDVTHgUc8N0qI0cF1Z1dTAEQ7CSgnBymZNr3BPSmtkNHL";
    public static String KSendGridAPIKey = "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";

//........ BrndBot fb App configuration.....uncomment for production server..../
/*   
    public static final String facebookString1 = "592852577521569";
    public static final String facebookString2 = "a87cc0c30d792fa5dd0aaef6b43994ef";
*/
//    Test fb App configuration.....comment for production server.....
    public static final String facebookString1 = "1577738335828079";
    public static final String facebookString2 = "2b6fd6252aa68e836acc574c0aa1a647";
    public static final String facebookPermissions = "publish_actions, publish_pages,manage_pages";
    
    public static final String TMP_FOLDER = BASE_UPLOAD_PATH + File.separator + "/tmp";
}
