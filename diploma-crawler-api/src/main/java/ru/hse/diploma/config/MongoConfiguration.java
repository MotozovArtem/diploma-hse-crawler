package ru.hse.diploma.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * Конфигурация подключения к MongoDB.
 *
 * @author Artem Motozov
 * @since 2021.04.30
 */
@Configuration
public class MongoConfiguration {
	@Bean
	public MongoDatabaseFactory mongoDatabaseFactory() {
		return new SimpleMongoClientDatabaseFactory(MongoClients.create("mongodb://admin:admin@localhost:27017"), "database");
	}
}
