package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.SliderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SliderTests extends BaseTest {

    @Test
    public void sliderTest() {
        getDriver().get("https://demoqa.com/slider");
        SliderPage sliderPage = new SliderPage(getDriver());
        
        String initialValue = sliderPage.getSliderValue();
        sliderPage.moveSlider(50);
        String newValue = sliderPage.getSliderValue();
        
        Assert.assertNotEquals(initialValue, newValue);
    }
}
