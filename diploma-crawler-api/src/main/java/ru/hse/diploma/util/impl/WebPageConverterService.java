package ru.hse.diploma.util.impl;

import ru.hse.diploma.domain.WebPage;
import ru.hse.diploma.domain.WebPageAnalyseResult;
import ru.hse.diploma.domain.WebPortal;
import ru.hse.diploma.repository.WebPageAnalyseResultRepository;
import ru.hse.diploma.repository.WebPortalRepository;
import ru.hse.diploma.util.ConverterService;
import ru.hse.diploma.view.WebPageViewItem;
import org.springframework.stereotype.Component;

/**
 * todo armotozov.
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
@Component
public class WebPageConverterService implements ConverterService<WebPage, WebPageViewItem> {

	/**
	 * todo armotozov.
	 */
	private final WebPortalRepository webPortalRepository;

	/**
	 * todo armotozov.
	 */
	private final WebPageAnalyseResultRepository webPageAnalyseResultRepository;

	public WebPageConverterService(WebPortalRepository webPortalRepository, WebPageAnalyseResultRepository webPageAnalyseResultRepository) {
		this.webPortalRepository = webPortalRepository;
		this.webPageAnalyseResultRepository = webPageAnalyseResultRepository;
	}

	@Override
	public WebPageViewItem toViewItem(WebPage webPage) {
		WebPageViewItem viewItem = new WebPageViewItem();
		viewItem.setId(webPage.getId());
		viewItem.setUrl(webPage.getUrl());
		viewItem.setResourceName(webPage.getResourceName());
		viewItem.setMetaData(webPage.getMetaData());
		viewItem.setHead(webPage.getHead());
		viewItem.setPageText(webPage.getPageText());

		if (webPage.getWebPortalId() != null) {
			WebPortal webPortal = webPortalRepository.findById(webPage.getWebPortalId()).orElseThrow();
			viewItem.setWebPortalId(webPage.getWebPortalId());
			viewItem.setWebPortalName(webPortal.getDomainName());
		}

		if (webPage.getWebPageAnalyseResultId() != null) {
			WebPageAnalyseResult analyseResult = webPageAnalyseResultRepository
					.findById(webPage.getWebPageAnalyseResultId()).orElseThrow();
			viewItem.setWebPageAnalyseResultId(webPage.getWebPageAnalyseResultId());
			viewItem.setWebPageAnalyseResultName(String.format("ID: %s [%s]",
					webPage.getWebPageAnalyseResultId(), analyseResult.getPhase().getPhaseName()));
		}
		viewItem.setCreationTime(webPage.getCreationTime());
		viewItem.setLastModifiedTime(webPage.getLastModifiedTime());
		viewItem.setTs(webPage.getTs());
		return viewItem;
	}

	@Override
	public WebPage toDomain(WebPageViewItem webPageViewItem) {
		throw new RuntimeException("Not implemented");
	}
}
