package ru.hse.diploma.repository;

import ru.hse.diploma.domain.WebPortal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий модели веб-портала.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
@RepositoryRestResource(collectionResourceRel = "web_portal", path = "web_portal")
public interface WebPortalRepository extends MongoRepository<WebPortal, String> {
}
