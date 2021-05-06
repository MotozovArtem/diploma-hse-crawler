package ru.hse.diploma.util.impl;

import ru.hse.diploma.domain.WebPortal;
import ru.hse.diploma.repository.WebPageRepository;
import ru.hse.diploma.util.ConverterService;
import ru.hse.diploma.view.WebPortalViewItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
@Component
public class WebPortalConverterService implements ConverterService<WebPortal, WebPortalViewItem> {

	/**
	 *
	 */
	private final WebPageRepository webPageRepository;

	/**
	 * Конструктор.
	 *
	 * @param webPageRepository
	 */
	public WebPortalConverterService(WebPageRepository webPageRepository) {
		this.webPageRepository = webPageRepository;
	}

	@Override
	public WebPortalViewItem toViewItem(WebPortal webPortal) {
		WebPortalViewItem viewItem = new WebPortalViewItem();
		viewItem.setId(webPortal.getId());
		viewItem.setPortalName(webPortal.getPortalName());
		viewItem.setDomainName(webPortal.getDomainName());
		viewItem.setUsedKeywords(webPortal.getUsedKeywords());
		List<WebPortalViewItem.WebPageIdAndResourceName> webPageIds = webPageRepository.findAllByWebPortalId(webPortal.getId())
				.stream()
				.map(webPage -> new WebPortalViewItem.WebPageIdAndResourceName(webPage.getId(), webPage.getResourceName()))
				.collect(Collectors.toList());
		viewItem.setWebPages(webPageIds);
		viewItem.setCreationTime(webPortal.getCreationTime());
		viewItem.setLastModifiedTime(webPortal.getLastModifiedTime());
		viewItem.setTs(webPortal.getTs());
		return viewItem;
	}

	@Override
	public WebPortal toDomain(WebPortalViewItem webPortalViewItem) {
		throw new RuntimeException("Not implementd");
	}
}
