package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinksPage {
    @FindBy(xpath = "//a[text()='Click Here for Broken Link']") private WebElement brokenLink;

    public BrokenLinksPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickBrokenLink() {
        brokenLink.click();
    }
}
