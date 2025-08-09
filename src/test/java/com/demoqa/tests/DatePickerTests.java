package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.DatePickerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatePickerTests extends BaseTest {

    @Test
    public void testSelectDate() {
        getDriver().get("https://demoqa.com/date-picker");
        DatePickerPage datePickerPage = new DatePickerPage(getDriver());

        datePickerPage.clickDateInput();
        String targetDate = "01/01/2024";
        datePickerPage.clearAndTypeDate(targetDate);

        Assert.assertEquals(datePickerPage.getDateValue(), targetDate);
    }
}
