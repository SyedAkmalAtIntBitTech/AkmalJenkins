/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import com.intbittech.AppConstants;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mohamed
 */
public class ServletUtil {

    public static Map<String, Object> getJsonFromRequestBody(HttpServletRequest request)
            throws IOException {
        return AppConstants.GSON.fromJson(
                new BufferedReader(request.getReader()), Map.class
        );
    }

    public static void printIllegalArgumentError(HttpServletResponse response, String errorMsg)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Map<String, Object> error = new HashMap<>();
        error.put("error", errorMsg);
        response.getWriter().write(AppConstants.GSON.toJson(error));
        response.getWriter().flush();
    }

    public static void printIllegalArgumentError(HttpServletResponse response,
            List<String> errorMsgs)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Map<String, Object> error = new HashMap<>();
        error.put("error", errorMsgs);
        response.getWriter().write(AppConstants.GSON.toJson(error));
        response.getWriter().flush();
    }

    public static boolean mapContainsKey(Map<String, Object> requestBodyMap, String key) {
        if (!requestBodyMap.containsKey(key)
                || requestBodyMap.get(key) == null
                || StringUtils.isEmpty(requestBodyMap.get(key).toString())) {
            return false;
        }
        return true;
    }

    public static void printSuccessData(HttpServletResponse response,
            Map<String, Object> data) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(AppConstants.GSON.toJson(data));
        response.getWriter().flush();
    }

    public static void printInternalException(HttpServletResponse response,
            String errorMsg) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", errorMsg);
        response.getWriter().write(AppConstants.GSON.toJson(errorMap));
        response.getWriter().flush();

    }

    public static String getServerName(ServletContext context) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            String path = context.getRealPath("")+"/static_resources/js/configurations.js";
// read script file
            engine.eval(Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8));

            Invocable inv = (Invocable) engine;
// call function from script file
            return inv.invokeFunction("getHost", "").toString();
        } catch (Exception ex) {
            return "http://clients.brndbot.com/BrndBot/";
        }
    }
    public static byte[] extractBytes2(String ImageName) throws IOException {
            File imgPath = new File(ImageName);
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            ByteOutputStream bos = null;
            try {
                bos = new ByteOutputStream();
                ImageIO.write(bufferedImage, "png", bos);
            } finally {
                try {
                    bos.close();
                } catch (Exception e) {
                }
            }
            return bos == null ? null : bos.getBytes();
        }
    public static String bytesTo64(byte[] ImageName) throws IOException {
        String url = "";
        try {
        url = "data:image/png;base64,"+ Base64.encode(ImageName);
        } catch (Exception e){

        }
        return url;
    }
    
    public static String getEmailHeader() throws IOException{
        String htmlHeader = "";
        File emailHeaderFile = new File(AppConstants.BASE_UPLOAD_PATH + File.separator+"emailheader.html");
        if(!emailHeaderFile.exists())
        {
            String emailHeaderString = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <meta charset=\"utf-8\"> <!-- utf-8 works for most cases --> <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn't be necessary --> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine --> <title></title> <!-- The title tag shows in email notifications, like Android 4.4. --> <!-- Web Font / @font-face : BEGIN --> <!-- NOTE: If web fonts are not required, lines 9 - 26 can be safely removed. --> <!-- Desktop Outlook chokes on web font references and defaults to Times New Roman, so we force a safe fallback font. --> <!--[if mso]> <style> * { font-family: sans-serif !important; } </style> <![endif]--> <!-- All other clients get the webfont reference; some will render the font and others will silently fail to the fallbacks. More on that here: http://stylecampaign.com/blog/2015/02/webfont-support-in-email/ --> <!--[if !mso]><!--> <!-- insert web font reference, eg: <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'> --> <!--<![endif]--> <!-- Web Font / @font-face : END --> <!-- CSS Reset --> <style type=\"text/css\"> /* What it does: Remove spaces around the email design added by some email clients. */ /* Beware: It can remove the padding / margin and add a background color to the compose a reply window. */ html, body { Margin: 0 !important; padding: 0 !important; height: 100% !important; width: 100% !important; } /* What it does: Stops email clients resizing small text. */ * { -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; } /* What it does: Forces Outlook.com to display emails full width. */ .ExternalClass { width: 100%; } /* What it does: Centers email on Android 4.4 */ div[style*=\"margin: 16px 0\"] { margin:0 !important; } /* What it does: Stops Outlook from adding extra spacing to tables. */ table, td { mso-table-lspace: 0pt !important; mso-table-rspace: 0pt !important; } /* What it does: Fixes webkit padding issue. Fix for Yahoo mail table alignment bug. Applies table-layout to the first 2 tables then removes for anything nested deeper. */ table { border-spacing: 0 !important; border-collapse: collapse !important; table-layout: fixed !important; Margin: 0 auto !important; } table table table { table-layout: auto; } /* What it does: Uses a better rendering method when resizing images in IE. */ img { -ms-interpolation-mode:bicubic; } /* What it does: Overrides styles added when Yahoo's auto-senses a link. */ .yshortcuts a { border-bottom: none !important; } /* What it does: A work-around for iOS meddling in triggered links. */ .mobile-link--footer a, a[x-apple-data-detectors] { color:inherit !important; text-decoration: underline !important; } </style> <!-- Progressive Enhancements --> <style> /* Media Queries */ @media screen and (max-width: 480px) { /* What it does: Forces elements to resize to the full width of their container. Useful for resizing images beyond their max-width. */ .fluid, .fluid-centered { width: 100% !important; max-width: 100% !important; height: auto !important; Margin-left: auto !important; Margin-right: auto !important; } /* And center justify these ones. */ .fluid-centered { Margin-left: auto !important; Margin-right: auto !important; } /* Change Padding on Mobile */ .mobile-padding { padding-left: 30px !important; padding-right: 30px !important; } .mobile-top-padding-30px { padding-top: 30px !important; } .mobile-bottom-padding-30px { bottom-top: 30px !important; } .mobile-top-padding-15px { padding-top: 15px !important; } .mobile-bottom-padding-15px { bottom-top: 15px !important; } /* What it does: shrink logo on mobile */ .shrink-logo { max-width: 150px !important; } /* What it does: Forces table cells into full-width rows. */ .stack-column, .stack-column-center { display: block !important; width: 100% !important; max-width: 100% !important; direction: ltr !important; } /* And center justify these ones. */ .stack-column-center { text-align: center !important; } /* What it does: Generic utility class for centering. Useful for images, buttons, and nested tables. */ .center-on-narrow { text-align: center !important; display: block !important; Margin-left: auto !important; Margin-right: auto !important; float: none !important; } table.center-on-narrow { display: inline-block !important; } /*What it does: Adjusts sizes of text on mobile screen sizes*/ .h-adj-30px { font-size: 30px !important; } .h-adj-25px { font-size: 25px !important; } .h-adj-20px { font-size: 20px !important; } .h-adj-18px { font-size: 18px !important; } .h-adj-16px { font-size: 16px !important; } .h-adj-14px { font-size: 14px !important; } .h-adj-12px { font-size: 12px !important; } .h-adj-10px { font-size: 10px !important; } } </style> </head> <body width=\"100%\" bgcolor=\"#EEEEEE\" style=\"Margin: 0;\">";
            emailHeaderFile.createNewFile();
            FileWriter emailHeaderWriter = new FileWriter(emailHeaderFile, false); // true to append
            // false to overwrite.
            emailHeaderWriter.write(emailHeaderString);
            emailHeaderWriter.close();
        }
        if(emailHeaderFile.exists())
            htmlHeader = FileUtils.readFileToString(emailHeaderFile, "UTF-8");
        return htmlHeader;
            
    }
    
    public static String convertHTMLToString(String fileName,ServletContext context)throws IOException {
        String htmlString = "";
        String path = context.getRealPath("")+"/WEB-INF/views/user/"+ fileName;
        File htmlFile = new File(path);
        if (htmlFile.exists()){
            htmlString = FileUtils.readFileToString(htmlFile, "UTF-8");
        }
        return htmlString;
    }
    
    public static String getJSONString(String fileName,ServletContext context)throws IOException {
        String jsonString = "";
        String path = context.getRealPath("")+"/static_resources/json/"+fileName;
        File jsonFile = new File(path);
        if (jsonFile.exists()){
            jsonString = FileUtils.readFileToString(jsonFile, "UTF-8");
        }
        return jsonString;
    }
}
