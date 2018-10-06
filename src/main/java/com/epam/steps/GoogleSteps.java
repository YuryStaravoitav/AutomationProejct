package com.epam.steps;

import com.epam.pages.GooglePage;
import com.epam.webdriver.WebDriverFactory;
import com.epam.webdriver.WebDriverType;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleSteps extends BaseSteps {

    private final GooglePage googlePage;

    private GoogleSteps() {
        googlePage = null;
    }

    public GoogleSteps(WebDriverType webDriverType) {
        googlePage = PageFactory.initElements(WebDriverFactory.INSTANCE.getInstance(webDriverType), GooglePage.class);
    }

    @Step("Open Search page")
    public void openSearchPage() {
        WebDriverFactory.INSTANCE.getWebDriver().get("http://google.com");
    }

    @Step("Search by value: '{0}'")
    public void search(String searchValue) {
        googlePage.search(searchValue);
    }

    @Step("Get search result titles")
    public List<String> getTitles() {
        return googlePage.getResultTitles();
    }
}