package com.epam.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum WebDriverFactory {

    INSTANCE;

    WebDriverFactory() {

    }

    public WebDriver getInstance(WebDriverType webDriverType) {
        switch (webDriverType) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
        }
        return null;
    }
}
