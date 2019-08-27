package com.browserstack.sample;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DataproviderClass {
    /**
     * @return Object[][] where first column contains 'author'
     * and second column contains 'searchKey'
     */

    @DataProvider(name = "SearchProvider")
    public static Object[][] getDataFromDataprovider() {
        return new Object[][]{
                {"Guru99", "India"},
                {"Krishna", "UK"},
                {"Bhupesh", "USA"}
        };
    }

    @DataProvider(name = "getData")
    public static Object[][] getData() throws FileNotFoundException {

        JsonElement jsonData = new JsonParser().parse(new FileReader("parameters.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("testParameters");
        List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}