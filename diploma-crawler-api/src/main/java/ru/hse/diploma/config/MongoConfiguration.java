package ru.hse.diploma.config;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.text.MessageFormat;

/**
 * Конфигурация подключения к MongoDB.
 *
 * @author Artem Motozov
 * @since 2021.04.30
 */
@Configuration
public class MongoConfiguration {
	@Value("${spring.data.mongodb.password}")
	private String password;

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.username}")
	private String username;

	@Value("${spring.data.mongodb.port}")
	private String port;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Bean
	public MongoDatabaseFactory mongoDatabaseFactory() {
		MessageFormat format = new MessageFormat("mongodb://{0}:{1}@{2}:{3}");
		return new SimpleMongoClientDatabaseFactory(
				MongoClients.create(format.format(new Object[]{username, password, host, port})),
				database);
	}
}
