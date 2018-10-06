package com.epam;

import com.epam.webdriver.WebDriverFactory;
import com.epam.webdriver.WebDriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Epic("EPAM")
@Feature("GOOGLE SEARCH")
@Story("Validate EPAM in Google search results")
class GoogleTest {

    private static WebDriver webDriver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = WebDriverFactory.INSTANCE.getInstance(WebDriverType.CHROME);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validate that ")
    void searchEpamInGoogleTest() {
        String searchString = "EPAM";
        webDriver.get("https://google.com");
        webDriver.findElement(By.name("q")).sendKeys(searchString);
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        webDriver.findElements(By.cssSelector(".LC20lb")).
                forEach(webElement -> assertThat(webElement.getText().toLowerCase(), containsString(searchString.toLowerCase())));
    }

    @AfterAll
    static void afterAll() {
        webDriver.quit();
    }
}
