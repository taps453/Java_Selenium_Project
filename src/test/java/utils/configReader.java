package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
	
	private static Properties prop;
	
	static {
		try {
			FileInputStream fileInputStream = new FileInputStream("src\\test\\resources\\config.properties");
			prop = new Properties();
			prop.load(fileInputStream);		
		}catch (IOException e) {
			throw new RuntimeException("Failed to load file", e);
		}
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
