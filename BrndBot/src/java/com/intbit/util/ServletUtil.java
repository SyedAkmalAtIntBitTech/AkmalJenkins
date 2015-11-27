/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import com.intbit.AppConstants;
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
            String path = context.getRealPath("")+"/js/configurations.js";
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
            String emailHeaderString = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /> <title>Idea Mail</title> <style type=\"text/css\"> body {margin:0px; padding:0px; background-color:#ffffff; color:#777777; font-family:Arial, Helvetica, sans-serif; font-size:12px; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; width:100% !important;} a, a:link, a:visited {color:#2e8b57; text-decoration:underline;} a:hover, a:active {text-decoration:none; color:#105e32 !important;} h1, h2, h3, h1 a, h2 a, h3 a {color:#2e8b57 !important;} h2 {padding:0px 0px 10px 0px; margin:0px 0px 10px 0px;} h2.name {padding:0px 0px 7px 0px; margin:0px 0px 7px 0px;} h3 {padding:0px 0px 5px 0px; margin:0px 0px 5px 0px;} p {margin:0 0 14px 0; padding:0;} img {border:0; -ms-interpolation-mode: bicubic; max-width:100%;} a img {border:none;} table td {border-collapse:collapse;} td.quote {font-family:Georgia, 'Times New Roman', Times, serif; font-size:18px; line-height:20pt; color:#2e8b57;} span.phone a, span.noLink a{color:2e8b57; text-decoration:none;} /* Hotmail */ .ReadMsgBody {width: 100%;} .ExternalClass {width: 100%;} /* / Hotmail */ /* Media queries */ @media (max-width: 767px) { td[class=shareContainer], td[class=topContainer], td[class=container] {padding-left:20px !important; padding-right:20px !important;} table[class=row] {width:100% !important; max-width:600px !important;} img[class=wideImage], img[class=banner] {width:100% !important; height:auto !important; max-width:100%;} } @media (max-width: 560px) { td[class=twoFromThree] {display:block; width:100% !important;} td[class=inner2], td[class=authorInfo] {padding-right:30px !important;} td[class=socialIconsContainer] {display:block; width:100% !important; border-top:0px !important;} td[class=socialIcons], td[class=socialIcons2] {padding-top:0px !important; text-align:left !important; padding-left:30px !important; padding-bottom:20px !important;} } @media (max-width: 480px) { html, body {margin-right:auto; margin-left:auto;} td[class=oneFromTwo] {display:block; width:100% !important;} td[class=inner] {padding-left:30px !important; padding-right:30px !important;} td[class=inner_image] {padding-left:30px !important; padding-right:30px !important; padding-bottom:25px !important;} img[class=wideImage] {width:auto !important; margin:0 auto;} td[class=viewOnline] {display:none !important;} td[class=date] {font-size:14px !important; padding:10px 30px !important; background-color:#f4f4f4; text-align:left !important;} td[class=title] {font-size:24px !important; line-height:32px !important;} table[class=quoteContainer] {width:100% !important; float:none;} td[class=quote] {padding-right:0px !important;} td[class=spacer] {padding-top:18px !important;} } @media (max-width: 380px) { td[class=shareContainer] {padding:0px 10px !important;} td[class=topContainer] {padding:10px 10px 0px 10px !important; background-color:#444444 !important;} td[class=container] {padding:0px 10px 10px 10px!important;} table[class=row] {min-width:240px !important;} img[class=wideImage] {width:100% !important; max-width:255px;} td[class=authorInfo], td[class=socialIcons2] {text-align:center !important;} td[class=spacer2] {display:none !important;} td[class=spacer3] {padding-top:23px !important;} table[class=iconContainer], table[class=iconContainer_right] {width:100% !important; float:none !important;} table[class=authorPicture] {float:none !important; margin:0px auto !important; width:80px !important;} td[class=icon] {padding:5px 0px 25px 0px !important; text-align:center !important;} td[class=icon] img {display:inline !important;} img[class=buttonRight] {float:none !important;} img[class=bigButton] {width:100% !important; max-width:224px; height:auto !important;} h2[class=website] {font-size:22px !important;} } /* / Media queries */ </style> <!-- Internet Explorer fix --> <!--[if IE]> <style type=\"text/css\"> @media (max-width: 560px) { td[class=twoFromThree], td[class=socialIconsContainer] {float:left; padding:0px;} } @media only screen and (max-width: 480px) { td[class=oneFromTwo] {float:left; padding:0px;} } @media (max-width: 380px) { span[class=phone] {display:block !important;} } </style> <![endif]--> <!-- / Internet Explorer fix --> <!-- Windows Mobile 7 --> <!--[if IEMobile 7]> <style type=\"text/css\"> td[class=shareContainer], td[class=topContainer], td[class=container] {padding-left:10px !important; padding-right:10px !important;} table[class=row] {width:100% !important; max-width:600px !important;} td[class=oneFromTwo], td[class=twoFromThree] {float:left; padding:0px; display:block; width:100% !important;} td[class=socialIconsContainer] {float:left; padding:0px; display:block; width:100% !important; border-top:0px !important;} td[class=socialIcons], td[class=socialIcons2] {padding-top:0px !important; text-align:left !important; padding-left:30px !important; padding-bottom:20px !important;} td[class=inner], td[class=inner2], td[class=authorInfo] {padding-left:30px !important; padding-right:30px !important;} td[class=inner_image] {padding-left:30px !important; padding-right:30px !important; padding-bottom:25px !important;} td[class=viewOnline] {display:none !important;} td[class=date] {font-size:14px !important; padding:10px 30px !important; background-color:#f4f4f4; text-align:left !important;} td[class=title] {font-size:24px !important; line-height:32px !important;} table[class=quoteContainer] {width:100% !important; float:none;} td[class=quote] {padding-right:0px !important;} td[class=spacer] {padding-top:18px !important;} span[class=phone] {display:block !important;} img[class=banner] {width:100% !important; height:auto !important; max-width:100%;} img[class=wideImage] {width:auto !important; margin:0 auto;} </style> <![endif]--> <!-- / Windows Mobile 7 --> <!-- Outlook --> <!--[if gte mso 15]> <style type=\"text/css\"> .iconContainer, .quoteContainer {mso-table-rspace:0px; border-right:1px solid #ffffff;} .iconContainer_right {mso-table-rspace:0px; border-right:1px solid #ffffff; padding-right:1px;} .authorPicture {mso-table-rspace:0px; border-right:1px solid #f4f4f4;} </style> <![endif]--> <!-- / Outlook --> </head> <body>";
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
}
