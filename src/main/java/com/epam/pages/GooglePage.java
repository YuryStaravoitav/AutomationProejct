package com.epam.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class GooglePage {

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(css = ".LC20lb")
    private List<WebElement> retultsList;

    public void search(String searchValue) {
        searchInput.sendKeys(searchValue);
        searchInput.sendKeys(Keys.ENTER);
    }

    public List<String> getResultTitles() {
        List<String> titles = new ArrayList<>();
        retultsList.forEach(webElement -> titles.add(webElement.getText()));
        return titles;
    }
}
