package com.epam;

import com.epam.steps.BaseSteps;
import com.epam.steps.GoogleSteps;
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

    private static GoogleSteps googleSteps = new GoogleSteps(WebDriverType.CHROME);

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validate that EPAM string contains in all search result titles")
    void searchEpamInGoogleTest() {
        String searchString = "EPAM";
        googleSteps.openSearchPage();
        googleSteps.search(searchString);
        googleSteps.getTitles().forEach(title -> assertThat(title.toLowerCase(), containsString(searchString.toLowerCase())));
    }

    @AfterAll
    static void afterAll() {
        googleSteps.close();
    }
}
