package com.intbit;

import com.divtohtml.StringUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
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

    private static final Logger logger = Logger.getLogger(PhantomImageConverter.class.getName());
    private static final String kEncoding = "UTF-8";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static String templateHTMLFilePath;
    private static String templateJSFilePath;
    private static String tempPath;
    private static String path = "";
    private String outputFilePath;

    public PhantomImageConverter(ServletContext context, String outputFilePath) throws Exception {
        this(context);
        this.outputFilePath = outputFilePath;                
        File outputFilePathDir = new File(outputFilePath);
        if (!outputFilePathDir.exists()){
            outputFilePathDir.mkdirs();
        }
    }
    
    public PhantomImageConverter(ServletContext context) throws Exception {

        path = context.getRealPath("");
        templateJSFilePath = path + "/template.js";
        templateHTMLFilePath = path + "/template.html";
        tempPath = AppConstants.LAYOUT_IMAGES_HOME + File.separator;
        File tempPathDir = new File(tempPath);
        if (!tempPathDir.exists()){
            tempPathDir.mkdirs();
        }
    }

    public File getImage(String htmlString, String width, String height, String x, String y) throws Exception {
        File createdHtmlFile = tempHTML(htmlString);
        File createdJSFile = tempJS(createdHtmlFile, width, height, x, y);

        Runtime runTime = Runtime.getRuntime();
        String execPath = "phantomjs " + createdJSFile.getPath();
 
        Process process = runTime.exec(execPath);
        int exitStatus = process.waitFor();
        File tempImagePath = new File(tempPath + File.separator + createdJSFile.getName().replace("js", "png"));
        logger.info(tempImagePath.getPath());
        
        File fileToSend = tempImagePath;
        if (!StringUtil.isEmpty(this.outputFilePath)) {
            File outputFile = new File(outputFilePath+File.separator+tempImagePath.getName());
            FileUtils.copyFile(tempImagePath, outputFile);
            fileToSend = outputFile;
            tempImagePath.delete();
        }
        
        createdHtmlFile.delete();
        createdJSFile.delete();

        return fileToSend;
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