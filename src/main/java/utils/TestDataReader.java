package utils;

import java.util.ResourceBundle;

public class TestDataReader {

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle(System.getProperty("env", "qa"));
    }

    public static String getTestData(String key) {
        System.out.println("ENV USED: " + System.getProperty("env"));
        return resourceBundle.getString(key);
    }
}
