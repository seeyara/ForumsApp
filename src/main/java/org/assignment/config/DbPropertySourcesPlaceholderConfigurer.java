package org.assignment.config;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class DbPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private String appName;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		Map<String, String> map;
		if (appName != null) {
			map = AppProperties.init(appName);
		} else {
			map = AppProperties.init();
		}
		final Properties dbProps = new Properties();

		dbProps.putAll(map);
		setProperties(dbProps);

		super.postProcessBeanFactory(beanFactory);

	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
