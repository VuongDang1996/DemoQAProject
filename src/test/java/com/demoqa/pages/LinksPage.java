package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
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
        ElementUtils.safeClick(driver, homeLink);
    }

    public void clickDynamicLink() {
        ElementUtils.safeClick(driver, dynamicLink);
    }
}
