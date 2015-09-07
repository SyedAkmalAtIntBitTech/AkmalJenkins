/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import com.intbit.AppConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mohamed
 */
public class ServletUtil {
    
    public static Map<String, Object> getJsonFromRequestBody(HttpServletRequest request) 
            throws IOException{
        return AppConstants.GSON.fromJson(
                new BufferedReader(request.getReader()), Map.class
        );
    }
    
    public static void printIllegalArgumentError(HttpServletResponse response, String errorMsg) 
            throws IOException{
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Map<String, Object> error = new HashMap<>();
        error.put("error", errorMsg);
        response.getWriter().write(AppConstants.GSON.toJson(error));
        response.getWriter().flush();
    }
    
     public static void printIllegalArgumentError(HttpServletResponse response, 
             List<String> errorMsgs) 
            throws IOException{
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Map<String, Object> error = new HashMap<>();
        error.put("error", errorMsgs);
        response.getWriter().write(AppConstants.GSON.toJson(error));
        response.getWriter().flush();
    }
     
     public static boolean mapContainsKey(Map<String, Object> requestBodyMap, String key){
        if ( !requestBodyMap.containsKey(key) || 
                requestBodyMap.get(key) == null ||
                StringUtils.isEmpty(requestBodyMap.get(key).toString())){
            return false;
        }
        return true;
    }
     
     public static void printSuccessData(HttpServletResponse response, 
             Map<String, Object> data) throws IOException{
         response.setStatus(HttpServletResponse.SC_OK);
         response.getWriter().write(AppConstants.GSON.toJson(data));
         response.getWriter().flush();
     }
     
     public static void printInternalException(HttpServletResponse response, 
             String errorMsg) throws IOException{
         response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         Map<String, Object> errorMap = new HashMap<>();
         errorMap.put("error", errorMsg);
         response.getWriter().write(AppConstants.GSON.toJson(errorMap));
         response.getWriter().flush();
         
     }
    
}
