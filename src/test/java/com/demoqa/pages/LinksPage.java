package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinksPage {
    private final WebDriver driver;

    @FindBy(id = "simpleLink") private WebElement homeLink;
    @FindBy(id = "dynamicLink") private WebElement dynamicLink;

    public LinksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickHomeLink() {
        homeLink.click();
    }

    public void clickDynamicLink() {
        dynamicLink.click();
    }
}
