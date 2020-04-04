package org.assignment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Application bean configuration.
 */

@Configuration
public class PFApplicationConfig {

	@Value("${application.environment}")
	private String environment;

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private Integer redisPort;

	@Value("${server.port}")
	private Integer tcpServerPort;

	public String getSessionRedisHost() {
		return redisHost;
	}

	public Integer getSessionRedisPort() {
		return redisPort;
	}

	public Integer getTcpServerPort() {
		return tcpServerPort;
	}

}
