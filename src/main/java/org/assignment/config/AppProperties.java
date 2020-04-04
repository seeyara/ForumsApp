package org.assignment.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read from sna_application_config table in archive db according to
 * application's name And also from java.properties. Setting GrayLog properties
 * from table. getProperty method returns value according to key which are set
 * in properties file
 */
public class AppProperties {

	private static HashMap<String, String> map = new HashMap<String, String>();

	private static final Logger LOGGER = LoggerFactory.getLogger(AppProperties.class);

	public static Map<String, String> init(String applicationName) {
		return init();
	}

	// building map according to application.properties and java.properties
	public static Map<String, String> init() {

		// for aplication.properties file
		try {
			util(ResourceBundle.getBundle("application"));
		} catch (MissingResourceException e) {
			LOGGER.info("Ignoring application.properties as file not found :" + e);
		}
		return map;
		
	}

	/**
	 * to load properties file content to map
	 */
	private static void util(ResourceBundle res) {
		Enumeration<String> keys = res.getKeys();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			map.put(key, res.getString(key));
		}
	}

	/**
	 * to get connection to archive db
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(map.get(Constants.CONFIG_DB_DRIVER));
		return DriverManager.getConnection(
				(map.get(Constants.CONFIG_DB_URL) + "/" + map.get(Constants.CONFIG_DB_SCHEMA)),
				map.get(Constants.CONFIG_DB_USER_NAME), map.get(Constants.CONFIG_DB_PASS));
	}

	/**
	 * get value for a key according to properties file
	 */
	public static String getProperty(String key) {
		return map.get(key);
	}

	/**
	 * Returns integer properties
	 * 
	 * @param key
	 * @return property
	 */
	public static Integer getPropertyInt(String key) {
		String value = map.get(key);
		if (value != null) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * Returns boolean properties
	 * 
	 * @param key
	 * @return property
	 */
	public static Boolean getPropertyBool(String key) {
		String value = map.get(key);
		if (value == null)
			return null;
		if ("TRUE".equalsIgnoreCase(value)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns list value of properties
	 * 
	 * @param key
	 * @return property
	 */
	public static List<String> getPropertyList(String key) {
		String value = map.get(key);
		if (value != null) {
			String[] properties = map.get(key).split(",");
			return Arrays.asList(properties);
		}
		return null;
	}

	/**
	 * return value for a key if it exist in properties file otherwise defualtValue
	 */
	public static String getProperty(String key, String defaultValue) {
		if (map.containsKey(key)) {
			return map.get(key);
		} else {
			return defaultValue;
		}
	}

	/**
	 * return all properties map
	 */
	public static Map<String, String> getPropertiesMap() {
		return map;
	}

}
