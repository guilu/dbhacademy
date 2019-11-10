package com.dbhacademy.firefight.selenium.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage {

    private final WebDriver driver;

    @FindBy(tagName = "a")
    private List<WebElement> links;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageAssert assertThat() {
        return new HomePageAssert(this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> getLinks() {
        return links;
    }

}