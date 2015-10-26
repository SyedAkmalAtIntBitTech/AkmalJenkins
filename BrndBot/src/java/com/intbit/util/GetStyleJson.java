/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import org.json.simple.JSONObject;

/**
 *
 * @author sandeep-kumar
 */
public class GetStyleJson implements Runnable{
public JSONObject JsonFonts;
public JSONObject JsonColors;
    @Override
    public void run() {
      JsonFonts= CustomStyles.getCustomFontsJson("path");
      JsonColors=CustomStyles.getCustomColorsJson("path");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   public JSONObject getJsonData()
   {
       
       GetStyleJson getStyleJson=new GetStyleJson();
       Thread thread=new Thread(getStyleJson);
       thread.start();
        return null;
   
   }
}
