package com.demoqa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatePickerPage {
    @FindBy(id = "datePickerMonthYearInput") private WebElement dateInput;

    public DatePickerPage(WebDriver driver) { PageFactory.initElements(driver, this); }

    public void clickDateInput() { dateInput.click(); }
    public void clearAndTypeDate(String date) {
        dateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateInput.sendKeys(Keys.DELETE);
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);
    }
    public String getDateValue() { return dateInput.getAttribute("value"); }
}
