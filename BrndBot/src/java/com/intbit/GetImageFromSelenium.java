/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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

    public static File getImage(String url) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("takesScreenshot", true);

        WebDriver driver = new RemoteWebDriver(new URL(
                "http://127.0.0.1:4444/wd/hub"), desiredCapabilities);

        driver.navigate().to(url);

        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        driver.quit();
        return srcFile;
    }
}
