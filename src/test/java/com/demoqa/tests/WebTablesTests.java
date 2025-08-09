package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.WebTablesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTablesTests extends BaseTest {

    @Test
    public void searchUserTest() {
        getDriver().get("https://demoqa.com/webtables");
        WebTablesPage webTablesPage = new WebTablesPage(getDriver());
        
        webTablesPage.searchForUser("Cierra");
        Assert.assertEquals(webTablesPage.getFirstRowFirstName(), "Cierra");
    }

    @Test
    public void addUserTest() {
        getDriver().get("https://demoqa.com/webtables");
        WebTablesPage webTablesPage = new WebTablesPage(getDriver());
        
        int initialRowCount = webTablesPage.getTableRowCount();
        webTablesPage.clickAddButton();
        webTablesPage.fillUserForm("John", "Smith", "john.smith@test.com", "30", "50000", "Engineering");
        
        Assert.assertEquals(webTablesPage.getTableRowCount(), initialRowCount + 1);
    }
}
