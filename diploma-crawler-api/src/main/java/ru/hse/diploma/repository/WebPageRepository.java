package ru.hse.diploma.repository;

import ru.hse.diploma.domain.WebPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Репозиторий модели веб-страницы.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
@RepositoryRestResource(collectionResourceRel = "web_page", path = "web_page")
public interface WebPageRepository extends MongoRepository<WebPage, String> {
	/**
	 * todo armotozov.
	 *
	 * @param webPortalId
	 * @return
	 */
	List<WebPage> findAllByWebPortalId(String webPortalId);
}
