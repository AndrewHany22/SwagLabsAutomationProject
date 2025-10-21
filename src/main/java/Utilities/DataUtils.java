package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    private static final  String TEST_DATA_PATH = "src/test/resources/Test-Data/";

    //TODO: reading data from json files
    public static String getJsonData (String filename , String field) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH+filename+ ".json");
        JsonElement jsonElement = JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(field).getAsString();
    }

    //TODO: reading data from properties files
    public static String getPropertyValue (String filename , String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH+filename + ".properties"));
        return properties.getProperty(key);
    }
}
