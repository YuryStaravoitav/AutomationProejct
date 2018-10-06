package com.epam.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum WebDriverFactory {

    INSTANCE;

    private WebDriver webDriver;

    WebDriverFactory() {

    }

    public WebDriver getInstance(WebDriverType webDriverType) {
        if (webDriver == null) {
            switch (webDriverType) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;
            }
            webDriver.manage().window().maximize();
        }

        return webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
