package com.browserstack.sample;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import org.testng.Assert;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
        driver.get("https://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("it works");
        element.submit();
        Thread.sleep(5000);

        Assert.assertEquals("it works - Google Search", driver.getTitle());

    }
}

