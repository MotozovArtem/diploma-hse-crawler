package ru.hse.diploma.repository;


import ru.hse.diploma.domain.WebPageBacklog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы с денормализованной сущностью зарегистрированных ошибок сбора данных.
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
@RepositoryRestResource(collectionResourceRel = "web_page_backlog", path = "web_page_backlog")
public interface WebPageBacklogRepository extends MongoRepository<WebPageBacklog, String> {
}
