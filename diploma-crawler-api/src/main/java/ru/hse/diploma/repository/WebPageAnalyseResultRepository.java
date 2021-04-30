package ru.hse.diploma.repository;

import ru.hse.diploma.domain.WebPageAnalyseResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для модели результатов анализа веб-страницы.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
@RepositoryRestResource(collectionResourceRel = "web_page_analyse", path = "web_page_analyse_result")
public interface WebPageAnalyseResultRepository extends MongoRepository<WebPageAnalyseResult, String> {
}
