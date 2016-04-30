/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import com.divtohtml.StringUtil;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import model.EmailInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author AR
 */
public class Utility {

    public static String getClassName(Class c) {
        String name = null;
        Class<?> enclosingClass = c.getEnclosingClass();
        if (enclosingClass != null) {
            name = enclosingClass.getName();
        } else {
            name = c.getName();

        }
        return name;
    }
    
    public static String getFullName(EmailInfo emailinfo){
        return emailinfo.getFirstName() + " " + emailinfo.getLastName();
    }
    
    public static String logMessage(Exception e, String message, String dbMessage) {
        String newMessage = message.concat(e.toString());
        if (dbMessage != null) {
            newMessage.concat(dbMessage);
        }
        return newMessage;
    }

    public static String injectFontsInHTML(JSONArray json_font_list, String context) {
        StringBuilder html_content = new StringBuilder();
        String server_name = getServerName(context);
        if (json_font_list != null) {
            for (Integer i = 0; i < json_font_list.size(); i++) {
                JSONObject json_font = (JSONObject) json_font_list.get(i);
                String font_name = (String) json_font.get("font_name");
                String font = (String) json_font.get("font_family_name");
                if (!StringUtil.isEmpty(font)) {
                    String[] font_family_name = font.split(",");
                    String google_key_word = font_family_name[0].replace(" ", "+");

                    Integer font_family_name_length = font_family_name.length;

                    if (font_family_name_length == 1) {
                        html_content.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=" + google_key_word + "\">\n");
                    } else {
                        html_content.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=" + google_key_word + "\">\n");

                        html_content.append("<style type=\"text/css\">\n"
                                + "            @font-face {\n"
                                + "                        font-family:" + font_name + ";\n"
                                + "                        src: url("+server_name+"DownloadFonts?file_name=" + font_family_name[1] + ");\n"
//                                + "                        src: url("+ AppConstants.BASE_FONT_UPLOAD_PATH + File.separator + font_family_name[1] + ");\n"
                                + "        </style>");
                    }
                }
            }

        }
        return html_content.toString();
    }
    
    public static String getServerName(String context) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            String path = context +"/js/configurations.js";
// read script file
            engine.eval(Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8));

            Invocable inv = (Invocable) engine;
// call function from script file
            return inv.invokeFunction("getHost", "").toString();
        } catch (Exception ex) {
            return "http://clients.brndbot.com/BrndBot/";
        }
    }
    public static String rgbToHex(String rgb){
        String colorcode=rgb.replace("rgb(", "").replace(")", "").replace(" ","");
        String Hex="#";
        String[] integervalue=colorcode.split(",");
        int r=Integer.parseInt(integervalue[0]);
        int g=Integer.parseInt(integervalue[1]);
        int b=Integer.parseInt(integervalue[2]);
       Hex= Hex.concat(Integer.toHexString(r));
       Hex= Hex.concat(Integer.toHexString(g));
       Hex= Hex.concat(Integer.toHexString(b));
        
       return Hex.toUpperCase();
    }
    
}