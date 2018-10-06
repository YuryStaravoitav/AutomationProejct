package com.epam.steps;

import com.epam.webdriver.WebDriverFactory;
import io.qameta.allure.Step;

public class BaseSteps {

    @Step("Close WebDriver")
    public void close() {
        WebDriverFactory.INSTANCE.getWebDriver().close();
    }
}
