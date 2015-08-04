package com.intbit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ilyas
 */
public class PhantomImageConverter {

    private static final String kEncoding = "UTF-8";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static String templateHTMLFilePath;
    private static String templateJSFilePath;
    private static String tempPath;
    private static String path = "";

    public PhantomImageConverter(ServletContext context) throws Exception {

        path = context.getRealPath(File.separator);
        templateJSFilePath = path + "/template.js";
        templateHTMLFilePath = path + "/template.html";
        tempPath = path + "/temp/";
    }

    public File getImage(String htmlString, String width, String height, String x, String y) throws Exception {
        File createdHtmlFile = tempHTML(htmlString);
        File createdJSFile = tempJS(createdHtmlFile, width, height, x, y);

        Runtime runTime = Runtime.getRuntime();
        String execPath = "phantomjs " + createdJSFile.getPath();
 
        Process process = runTime.exec(execPath);
        int exitStatus = process.waitFor();
        File tempImagePath = new File(tempPath + createdJSFile.getName().replace("js", "png"));
        createdHtmlFile.delete();
        createdJSFile.delete();

        return tempImagePath;
    }

    private File tempHTML(String bodyString) throws IOException {

        File htmlTemplateFile = new File(templateHTMLFilePath);
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, kEncoding);
        htmlString = htmlString.replace("$body", bodyString);
        String fileName = dateFormat.format(new Date());
        //Using the date as the unique file name
        File newHtmlFile = new File(tempPath + fileName + ".html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString, kEncoding);
        return newHtmlFile;
    }

    private File tempJS(File temp_html_file, String width, String height, String top, String left) throws IOException {

        File jsTemplateFile = new File(templateJSFilePath);
        String jsString = FileUtils.readFileToString(jsTemplateFile, kEncoding);
        jsString = jsString.replace("replace_html_location", temp_html_file.getPath());
        String fileName = temp_html_file.getName();
        jsString = jsString.replace("replace_html_image", temp_html_file.getPath().replace(".html", ""));
        jsString = jsString.replace("replace_width", width);
        jsString = jsString.replace("replace_height", height);
        jsString = jsString.replace("replace_top", top);
        jsString = jsString.replace("replace_left", left);
        //Using the date as the unique file name
        File newJSFile = new File(tempPath + fileName.replace(".html", "") + ".js");
        FileUtils.writeStringToFile(newJSFile, jsString, kEncoding);
        return newJSFile;
    }
}