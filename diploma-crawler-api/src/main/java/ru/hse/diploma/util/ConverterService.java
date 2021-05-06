package ru.hse.diploma.util;

import ru.hse.diploma.domain.DomainObject;
import org.springframework.stereotype.Component;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
@Component
public interface ConverterService<Domain extends DomainObject, ViewItem> {

	ViewItem toViewItem(Domain domain);

	Domain toDomain(ViewItem viewItem);
}
