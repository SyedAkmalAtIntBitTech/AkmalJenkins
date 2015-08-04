/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author AR
 */
public class GetImageFromSelenium {
   static String fileName="";
    private static final String kEncoding = "UTF-8";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ssSSS");

    public static File getImage(String htmlString) throws MalformedURLException, IOException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("takesScreenshot", true);

        WebDriver driver = new RemoteWebDriver(new URL(
                "http://127.0.0.1:4444/wd/hub"), desiredCapabilities);

        File createdHtmlFile = tempHTML(htmlString);
  
//       driver.get(createdHtmlFile.getPath());

//        driver = new Augmenter().augment(driver);
        
        
        driver.get("file:///home/sandeep-kumar/NetBeansProjects/BrndBot/BrndBot/web/temp/"+fileName+".html");
        
//        File srcFile = ((TakesScreenshot) driver)
//                .getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(srcFile, new File(
//                               "/home/sandeep-kumar/NetBeansProjects/BrndBot/BrndBot/web/temp/"+fileName+".png"));
//        
//        
//driver.get("http://localhost:8080/BrndBot/dashboard.html");
        File screenshoot=new File(fileName+".png");
WebElement ele = driver.findElement(By.className("imagediv"));   
//Get entire page screenshot
File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
BufferedImage  fullImg = ImageIO.read(screenshot);
//Get the location of element on the page
Point point = ele.getLocation();
//Get width and height of the element
int eleWidth = ele.getSize().getWidth();
int eleHeight = ele.getSize().getHeight();
//Crop the entire page screenshot to get only element screenshot
BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth,
    eleHeight);
ImageIO.write(eleScreenshot, "png", screenshot);
//Copy the element screenshot to disk
FileUtils.copyFile(screenshot, new File("/home/sandeep-kumar/NetBeansProjects/BrndBot/BrndBot/web/temp/"+fileName+".png"));
        driver.quit();
        //Deleting file the using it
//       createdHtmlFile.delete();
        return screenshoot;
    }

    public static File tempHTML(String bodyString) throws IOException {

        File htmlTemplateFile = new File("/home/sandeep-kumar/NetBeansProjects/BrndBot/BrndBot/web/admin/template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, kEncoding);
        htmlString = htmlString.replace("$body", bodyString);
         fileName = dateFormat.format(new Date());
        //Using the date as the unique file name
        File newHtmlFile = new File("/home/sandeep-kumar/NetBeansProjects/BrndBot/BrndBot/web/temp/"+fileName+".html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString, kEncoding);
        return newHtmlFile;
    }
}
