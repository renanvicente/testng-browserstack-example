package com.browserstack.sample;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static team.zag.testng.utils.IConstants.SESSION_ID;


import java.util.concurrent.TimeUnit;

public class SingleTest extends BrowserStackTestNGTest {


    @Test(dataProvider="getData",dataProviderClass=DataproviderClass.class)
    public void test(TestData data, ITestContext context) throws InterruptedException {
        context.setAttribute(SESSION_ID, ((RemoteWebDriver)driver).getSessionId().toString());

        String author = "";
        String searchKey = "";
        for(TestParameters testParameters: data.getTestParameters()) {
            switch(testParameters.getParameterKey()) {
                case "author":
                    author = testParameters.getParameterValue();
                    break;
                case "searchKey":
                    searchKey = testParameters.getParameterValue();
                    break;
            }
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://google.com");

        WebElement searchText = driver.findElement(By.name("q"));
        //Searching text in google text box
        searchText.sendKeys(searchKey);
        System.out.println("Welcome -> " + author + " Your search key is -> " + searchKey);
        System.out.println("Thread will sleep now");
        Thread.sleep(3000);

        String testValue = searchText.getAttribute("value");
        System.out.println(testValue + "::::" + searchKey);
        searchText.clear();

        //Verify if the value in google search box is correct
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));

    }

}

