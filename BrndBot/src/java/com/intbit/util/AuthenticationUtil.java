/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import com.intbit.AppConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohamed
 */
public class AuthenticationUtil {
    
    public static boolean isUserLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (session.getAttribute("UID") != null);
    }
    
    public static void printAuthErrorToResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        Map<String, Object> error = new HashMap<>();
        error.put("error", "User is not logged in");
        response.getWriter().write(AppConstants.GSON.toJson(error));
        response.getWriter().flush();
    }
    
    public static Integer getUUID(HttpServletRequest request){
         HttpSession session = request.getSession();
         return Integer.parseInt(session.getAttribute("UID").toString());
    }
    
}
