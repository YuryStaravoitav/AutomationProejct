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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Tag("all")
@Epic("EPAM")
@Feature("GOOGLE SEARCH")
@Story("Validate EPAM in Google search results")
class GoogleSearchReflectionTest {

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
    void searchEpamInGoogleByReflectionTest() throws InvocationTargetException, IllegalAccessException {
        String searchString = "EPAM";
        List<Method> methodList = Arrays.asList(googleSteps.getClass().getMethods());

        //  googleSteps.openSearchPage(); invoke by reflection
        methodList.stream().filter(method -> method.getName().equals("openSearchPage")).
                findFirst().get().invoke(googleSteps);

        // googleSteps.search(searchString); invoke by reflection
        methodList.stream().filter(method -> method.getName().equals("search")).
                findFirst().get().invoke(googleSteps, searchString);

        // googleSteps.getTitles(); invoke by reflection
        List<String> titles = (List<String>) methodList.stream()
                .filter(method -> method.getName().equals("getTitles")).
                        findFirst().get().invoke(googleSteps);

        titles.forEach(title -> assertThat(title.toLowerCase(), containsString(searchString.toLowerCase())));
    }


    @AfterAll
    static void afterAll() throws InvocationTargetException, IllegalAccessException {
        // googleSteps.close(); invoke by reflection
        Arrays.asList(googleSteps.getClass().getMethods()).stream()
                .filter(method -> method.getName().equals("close")).
                findFirst().get().invoke(googleSteps);
    }
}
