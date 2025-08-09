package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.DroppablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DroppableTests extends BaseTest {

    @Test
    public void testDragAndDrop() {
        getDriver().get("https://demoqa.com/droppable");
        DroppablePage droppablePage = new DroppablePage(getDriver());
        droppablePage.dragAndDrop();
        Assert.assertEquals(droppablePage.getDroppableText().trim(), "Dropped!");
    }
}
