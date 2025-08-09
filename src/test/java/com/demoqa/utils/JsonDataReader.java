package com.demoqa.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading JSON test data files
 * Provides generic methods to parse JSON data for TestNG data providers
 */
public class JsonDataReader {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Reads JSON file and returns data as Object[][] for TestNG DataProvider
     * @param fileName the JSON file name (without .json extension)
     * @return Object[][] array for TestNG DataProvider
     */
    public static Object[][] getTestData(String fileName) {
        try {
            List<Map<String, Object>> dataList = readJsonFile(fileName);
            Object[][] testData = new Object[dataList.size()][1];
            
            for (int i = 0; i < dataList.size(); i++) {
                testData[i][0] = dataList.get(i);
            }
            
            return testData;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data from " + fileName + ".json", e);
        }
    }
    
    /**
     * Reads JSON file and returns data as Object[][] with multiple parameters for TestNG DataProvider
     * @param fileName the JSON file name (without .json extension)
     * @param parameterNames ordered list of parameter names to extract from JSON
     * @return Object[][] array for TestNG DataProvider with individual parameters
     */
    public static Object[][] getTestData(String fileName, String... parameterNames) {
        try {
            List<Map<String, Object>> dataList = readJsonFile(fileName);
            Object[][] testData = new Object[dataList.size()][parameterNames.length];
            
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> dataMap = dataList.get(i);
                for (int j = 0; j < parameterNames.length; j++) {
                    testData[i][j] = dataMap.get(parameterNames[j]);
                }
            }
            
            return testData;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data from " + fileName + ".json", e);
        }
    }
    
    /**
     * Reads JSON file and returns data as List of Maps
     * @param fileName the JSON file name (without .json extension)
     * @return List of Maps containing the JSON data
     */
    public static List<Map<String, Object>> readJsonFile(String fileName) throws IOException {
        String filePath = "testdata/" + fileName + ".json";
        InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(filePath);
        
        if (inputStream == null) {
            throw new IOException("Cannot find resource file: " + filePath);
        }
        
        TypeReference<List<Map<String, Object>>> typeReference = new TypeReference<List<Map<String, Object>>>() {};
        return objectMapper.readValue(inputStream, typeReference);
    }
    
    /**
     * Reads JSON file and maps to specific POJO class
     * @param fileName the JSON file name (without .json extension)
     * @param clazz the target class to map JSON data to
     * @param <T> the type of the target class
     * @return List of objects of type T
     */
    public static <T> List<T> readJsonFile(String fileName, Class<T> clazz) throws IOException {
        String filePath = "testdata/" + fileName + ".json";
        InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(filePath);
        
        if (inputStream == null) {
            throw new IOException("Cannot find resource file: " + filePath);
        }
        
        TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {};
        return objectMapper.readValue(inputStream, typeReference);
    }
    
    /**
     * Gets test data for specific test method from JSON file
     * @param context TestNG context to get test method name
     * @param fileName the JSON file name (without .json extension)
     * @return Object[][] array for TestNG DataProvider
     */
    public static Object[][] getTestDataForMethod(ITestContext context, String fileName) {
        return getTestData(fileName);
    }
}
