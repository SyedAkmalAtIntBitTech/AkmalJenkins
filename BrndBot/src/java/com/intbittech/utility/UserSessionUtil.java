/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.intbittech.model.UserProfile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Haider Khan @ Intbit
 */
public class UserSessionUtil {
    
      public static Object getLogedInUser() {
        UserProfile userProfile = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userProfile = ((UserProfile) principal);
            return userProfile;
        } 
        else{
            return principal.toString();
        }
        
    }
    
}
