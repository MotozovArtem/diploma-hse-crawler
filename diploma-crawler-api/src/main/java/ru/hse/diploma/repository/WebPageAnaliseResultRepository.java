package ru.hse.diploma.repository;

import ru.hse.diploma.domain.WebPageAnaliseResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
@RepositoryRestResource(collectionResourceRel = "web_page_analise", path = "web_page_analise_result")
public interface WebPageAnaliseResultRepository extends MongoRepository<WebPageAnaliseResult, String> {
}
