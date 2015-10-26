/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import com.intbit.AppConstants;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *this file will have a static reference to colors and fonts
 * @author sandeep-kumar
 */
public class CustomStyles {
    private  static  JSONObject customColors;
    private  static  JSONObject customFonts;
    public static JSONObject getCustomColorsJson(String path){
      if(customColors==null){
       customColors = new JSONObject();
        try {
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        customColors = (JSONObject) parser.parse(fileReader);
//        JSONArray ColorArray = (JSONArray) json.get("admincustomcolors");
//        Iterator i = ColorArray.iterator();
//        while (i.hasNext()) { 
//            System.out.println(" " + i.next()); 
//        }
        } catch (Exception ex) 
        { 
            ex.printStackTrace();
        }

       
    }
       return customColors;
    }
    public static JSONObject getCustomFontsJson(String path){
        if(customFonts==null){
       customFonts = new JSONObject();
        try {
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(path);
        customFonts = (JSONObject) parser.parse(fileReader);
        } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        
        }
        return customFonts;
    }
    

    
}
