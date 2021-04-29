package ru.hse.diploma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

/**
 * Конфигурация фабрики клиентов MongoDB.
 *
 * @author Artem Motozov
 * @since 2021.04.30
 */
@Configuration
public class AppConfig {

	@Bean
	public MongoClientFactoryBean mongoClientFactoryBean() {
		MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
		mongoClientFactoryBean.setHost("localhost");
		mongoClientFactoryBean.setPort(27017);
		return mongoClientFactoryBean;
	}
}
