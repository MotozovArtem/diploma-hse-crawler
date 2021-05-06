package ru.hse.diploma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

/**
 * Конфигурация фабрики клиентов MongoDB.
 *
 * @author Artem Motozov
 * @since 2021.04.30
 */
@Configuration
@ComponentScan(basePackages = {"ru.hse.diploma.controller", "ru.hse.diploma.util", "ru.hse.diploma.util.impl"})
public class AppConfig {

	@Value("${spring.data.mongodb.port}")
	private Integer port;

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Bean
	public MongoClientFactoryBean mongoClientFactoryBean() {
		MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
		mongoClientFactoryBean.setHost(host);
		mongoClientFactoryBean.setPort(port);
		return mongoClientFactoryBean;
	}
}
