/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.util;

import com.intbit.AppConstants;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
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

}
