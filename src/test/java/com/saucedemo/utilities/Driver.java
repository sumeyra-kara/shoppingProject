package com.saucedemo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private Driver(){} // baska classlardan obje olusturulmasin diye
    private static WebDriver driver; // classname ile cagirmam icin static yapiyoruz
    // encapsülation kullanmak istedigimden private oluyor

    public static WebDriver get(){
        if (driver==null){
            String browser = ConfigReader.get("browser");
            switch (browser.toLowerCase()){
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "chrome-headless":
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "firefox-headless":
                    driver =new FirefoxDriver(new FirefoxOptions().addArguments("--headless"));
                    break;
                case "edge":
                    if (System.getProperty("os.name").contains("MAC")){ // mac'de edge calismadigi icin
                        throw new WebDriverException("Your Operating System does not support");
                    }
                    driver = new EdgeDriver();
                    break;
                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                    driver = new InternetExplorerDriver();
                    break;
                case "safari" :
                    if (System.getProperty("os.name").contains("WINDOWS")){
                        throw new WebDriverException("Your Operating System does not support");
                    }
                    driver=new SafariDriver();
                    break;
                default:
                    System.out.println("Invalid driver");
            }

        }

        return driver;
    }
    public static void closeDriver(){
        if (driver!=null){
            //driver.quit();
            driver=null;
        }
    }
}
