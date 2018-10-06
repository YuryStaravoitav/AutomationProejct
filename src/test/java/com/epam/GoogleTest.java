package com.epam;

import com.epam.steps.GoogleSteps;
import com.epam.webdriver.WebDriverFactory;
import com.epam.webdriver.WebDriverType;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Tag("all")
@Epic("EPAM")
@Feature("GOOGLE SEARCH")
@Story("Validate EPAM in Google search results")
class GoogleTest {

    private static GoogleSteps googleSteps = null;

    @BeforeAll
    static void beforeAll() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor[] constructor = GoogleSteps.class.getConstructors();
        for (int i = 0; i < constructor.length; i++) {
            System.out.println(constructor[i].getName());
            System.out.println(constructor[i].getParameterCount());
            System.out.println(constructor[i].getParameterTypes()[0].getName());
        }
        googleSteps = (GoogleSteps) constructor[0].newInstance(WebDriverType.CHROME);
    }

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
