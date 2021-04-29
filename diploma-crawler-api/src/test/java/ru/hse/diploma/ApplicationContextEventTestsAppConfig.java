package ru.hse.diploma;

import static java.util.Collections.singletonList;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.04.30
 */
public class ApplicationContextEventTestsAppConfig extends AbstractMongoClientConfiguration {
	@Override
	protected String getDatabaseName() {
		return "database";
	}

	@Override
	protected void configureClientSettings(MongoClientSettings.Builder builder) {
		builder
				.credential(MongoCredential.createCredential("admin", "database", "admin".toCharArray()))
				.applyToClusterSettings((settings) -> {
					settings.hosts(singletonList(new ServerAddress("localhost", 27017)));
				});
	}
}
