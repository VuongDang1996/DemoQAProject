package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinksPage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[text()='Click Here for Broken Link']") private WebElement brokenLink;

    public BrokenLinksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickBrokenLink() {
        ElementUtils.safeClick(driver, brokenLink);
    }
}
