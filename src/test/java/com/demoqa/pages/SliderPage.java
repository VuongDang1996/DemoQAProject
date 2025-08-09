package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderPage {
    private final WebDriver driver;

    @FindBy(css = ".range-slider") private WebElement sliderHandle;
    @FindBy(id = "sliderValue") private WebElement sliderValue;

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void moveSlider(int xOffset) {
        new Actions(driver).dragAndDropBy(sliderHandle, xOffset, 0).perform();
    }

    public String getSliderValue() {
        return sliderValue.getAttribute("value");
    }
}
