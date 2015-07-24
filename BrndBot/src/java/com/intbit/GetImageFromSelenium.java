/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author AR
 */
public class GetImageFromSelenium {

    private static final String kEncoding = "UTF-8";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ssSSS");

    public static File getImage(String htmlString) throws MalformedURLException, IOException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("takesScreenshot", true);

        WebDriver driver = new RemoteWebDriver(new URL(
                "http://127.0.0.1:4444/wd/hub"), desiredCapabilities);

        File createdHtmlFile = tempHTML(htmlString);
        driver.navigate().to(createdHtmlFile.getPath());

        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        driver.quit();
        //Deleting file the using it
        createdHtmlFile.delete();
        return srcFile;
    }

    public static File tempHTML(String bodyString) throws IOException {

        File htmlTemplateFile = new File("admin/template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, kEncoding);
        htmlString = htmlString.replace("$body", bodyString);
        String fileName = dateFormat.format(new Date());
        //Using the date as the unique file name
        File newHtmlFile = new File("temp/"+fileName+".html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString, kEncoding);
        return newHtmlFile;
    }
}
