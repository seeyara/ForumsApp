package org.assignment.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import org.assignment.service.MigratorService;

@SpringBootApplication
@ComponentScan("org.assignment")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, PropertyPlaceholderAutoConfiguration.class })
public class ForumServer implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForumServer.class);

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Autowired
	private MigratorService migratorService;

	public static void main(String[] args) {
		try {
			SpringApplication.run(ForumServer.class, args);
		} catch (Throwable e) {
			LOGGER.error("Exception in forums server, ", e);
		}
	}

	@Override
	public void run(String... arg0) throws Exception {

		if (arg0.length > 0 && "migrate".equals(arg0[0])) {
			migratorService.runMigration(arg0);
		}
		registerShutDownHook();

		LOGGER.info("Started forums service v1");
	}

	/**
	 * Shuts down the relevant resources gracefully.
	 */
	private void registerShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				applicationContext.close();
			}
		});
	}
}
