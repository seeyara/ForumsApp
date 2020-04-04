package org.assignment.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Constants {

	public static final String CONFIG_DB_DRIVER = "database.driver";
	public static final String CONFIG_DB_URL = "database.master.url";
	public static final String CONFIG_DB_SCHEMA = "database.master.schema";
	public static final String CONFIG_DB_USER_NAME = "database.master.username";
	public static final String CONFIG_DB_PASS = "database.master.password";
	public static final String CONFIG_COMMON_PROP = "common";
	
	// AWS Secret Manager Credentials

	public static String REGION = "ap-south-1";
	public static final ObjectMapper JSON_MAPPPER = new ObjectMapper();
}
