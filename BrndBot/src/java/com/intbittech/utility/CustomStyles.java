/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import java.io.FileReader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *this file will have a static reference to colors and fonts
 * @author sandeep-kumar
 */
public class CustomStyles {
    private  static  JSONObject customColors;
    private  static  JSONObject customFonts;
    public static JSONObject getCustomColorsJson(HttpServletRequest request){
      if(customColors==null){
       customColors = new JSONObject();
        try {
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(getCustomColorsFilePath(request));
        customColors = (JSONObject) parser.parse(fileReader);
        } catch (Exception ex) 
        { 
            ex.printStackTrace();
        }

       
    }
       return customColors;
    }
    public static JSONObject getCustomFontsJson(HttpServletRequest request){
        if(customFonts==null){
       customFonts = new JSONObject();
        try {
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(getCustomFontsFilePath(request));
        customFonts = (JSONObject) parser.parse(fileReader);
        } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        
        }
        return customFonts;
    }
    public static String getCustomColorsFilePath(HttpServletRequest request)
            
    {
       ServletContext servletContext = request.getSession().getServletContext();
      String relativeWebPath = "/admin/colors/CustomColors.json";
      String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
    
    return absoluteDiskPath;
    }
    
     public static String getCustomFontsFilePath(HttpServletRequest request)
            
    {
       ServletContext servletContext = request.getSession().getServletContext();
      String relativeWebPath = "/admin/colors/CustomFonts.json";
      String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
    
    return absoluteDiskPath;
    }
    
            
            
            
    

    
}
