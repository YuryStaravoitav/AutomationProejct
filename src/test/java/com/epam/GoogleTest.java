package com.epam;

import com.epam.webdriver.WebDriverFactory;
import com.epam.webdriver.WebDriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

class GoogleTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = WebDriverFactory.INSTANCE.getInstance(WebDriverType.CHROME);
    }

    @Test
    public void googleSearchTest() {
        webDriver.get("https://google.com");
    }

    @AfterAll
    public static void afterAll() {
        webDriver.quit();
    }
}
