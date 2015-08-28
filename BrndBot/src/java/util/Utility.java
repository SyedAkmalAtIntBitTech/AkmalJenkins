/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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

    public static String logMessage(Exception e, String message, String dbMessage) {
        String newMessage = message.concat(e.toString());
        if (dbMessage != null) {
            newMessage.concat(dbMessage);
        }
        return newMessage;
    }

    public static String injectFontsInHTML(JSONArray json_font_list) {
        StringBuilder html_content = new StringBuilder();
        if (json_font_list != null) {
            for (Integer i = 0; i < json_font_list.size(); i++) {
                JSONObject json_font = (JSONObject) json_font_list.get(i);
                String font_name = (String) json_font.get("font_name");
                String font = (String) json_font.get("font_family_name");
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
                            + "                        src: url(/BrndBot/DownloadFonts?file_name=" + font_family_name[1] + ");\n"
                            + "            }\n"
                            + "        </style>");
                }

            }

        }
        return html_content.toString();
    }
}
