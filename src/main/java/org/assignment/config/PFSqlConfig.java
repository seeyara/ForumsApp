package org.assignment.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SQL bean configuration.
 *
 */

@Configuration
public class PFSqlConfig {

	@Value("${database.master.url}")
	private String databaseURL;

	@Value("${database.master.schema}")
	private String databaseSchema;

	@Value("${database.master.username}")
	private String databaseUsername;

	@Value("${database.master.password}")
	private String databasePassword;

	@Bean(destroyMethod = "")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(databaseURL + "/" + databaseSchema);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);

		return dataSource;
	}
	
	@Bean
	public static PropertyPlaceholderConfigurer placeHolderConfigurer() {
		DbPropertySourcesPlaceholderConfigurer configurer = new DbPropertySourcesPlaceholderConfigurer();

		configurer.setIgnoreUnresolvablePlaceholders(true);
		configurer.setIgnoreResourceNotFound(true);
		configurer.setPlaceholderPrefix("${");
		configurer.setPlaceholderSuffix("}");
		configurer.setAppName("Forums Service");
		return configurer;
	}

}
