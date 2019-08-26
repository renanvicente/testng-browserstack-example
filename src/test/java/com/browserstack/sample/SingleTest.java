package com.browserstack.sample;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("parameters.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = ( JSONArray ) jsonObject.get("testParameters");

            for ( Object o: data) {
                System.out.println(o);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        driver.get("https://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        SessionId session = ((RemoteWebDriver)driver).getSessionId();
        System.out.println("Session id: " + session.toString());

        element.sendKeys("it works");
        element.submit();
        Thread.sleep(5000);

        Assert.assertEquals("it works - Google Search", driver.getTitle());

    }

//    private static void parseTestParametersObject(JSONObject employee)
//    {
//        //Get employee object within list
//        JSONObject employeeObject = (JSONObject) employee.get("testParameters");
//
//        //Get employee first name
//        String firstName = (String) employeeObject.get("testName");
//        System.out.println(firstName);
//
//        //Get employee last name
//        String lastName = (String) employeeObject.get("testFunctionName");
//        System.out.println(lastName);
//
//        //Get employee website name
//        JSONObject website = (JSONObject) employeeObject.get("testParameters");
//        System.out.println(website);
//    }
}

