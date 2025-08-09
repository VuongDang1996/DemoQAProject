package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.SortablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortableTests extends BaseTest {

    @Test
    public void sortableTest() {
        getDriver().get("https://demoqa.com/sortable");
        SortablePage sortablePage = new SortablePage(getDriver());
        
        String originalFirstItem = sortablePage.getFirstItemText();
        String originalSecondItem = sortablePage.getSecondItemText();
        
        sortablePage.dragFirstItemToSecondPosition();
        
        // Verify the order has changed
        String newFirstItem = sortablePage.getFirstItemText();
        Assert.assertNotEquals(originalFirstItem, newFirstItem);
    }
}
