package com.jorisboschmans.chatgptportal.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The ConfigManager class is responsible for loading a properties file
 * and providing access to its values. The properties file is specified
 * via the "profile" system property, or a default properties file is used
 * if the "profile" property is not set.
 *
 * @author Joris Boschmans
 * @version 1.0
 * @since 2023-07-19
 */
public class PropManager {
    private static final Properties properties = new Properties();
		private static final Logger logger = LoggerFactory.getLogger(PropManager.class);
	
	static {
		init();
	}
	
	private PropManager() {}
	
	/**
	 * Initializes the properties by loading them from a file.
	 * The properties file name is determined by the "profile" system property. If the "profile"
	 * property is not set, it defaults to "default.properties". If the specified profile name
	 * doesn't end with ".properties", the extension is appended.
	 * If there's any error in loading the properties file, a runtime exception is thrown.
	 */
	private static void init() {
        String propertiesFilename = System.getProperty("profile", "default");

        // Append .properties if not present
        if (!propertiesFilename.endsWith(".properties")) {
            propertiesFilename += ".properties";
        }

        // Load the properties
        try (InputStream input = PropManager.class.getClassLoader().getResourceAsStream(propertiesFilename)) {
	        logger.debug("Reading {}", propertiesFilename);
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading properties file " + propertiesFilename, ex);
        }
    }

    /**
     * This method is used to get the property value for the given key.
     * It returns null if the property is not found.
     *
     * @param key This is the key to look up in the properties.
     * @return String This returns the property value for the given key.
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * This method is used to get the property value for the given key.
     * It returns the given defaultKey if the property is not found.
     *
     * @param key This is the key to look up in the properties.
     * @param defaultKey This is the String that will be returned if `key` cannot be found.
     * @return String This returns the property value for the given key or the given `defaultKey` value.
     */
    public static String get(String key, String defaultKey) {
        return properties.getProperty(key, defaultKey);
    }
}
