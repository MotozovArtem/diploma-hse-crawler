package ru.hse.diploma.util.impl;

import ru.hse.diploma.domain.WebPage;
import ru.hse.diploma.domain.WebPageAnalyseResult;
import ru.hse.diploma.repository.WebPageRepository;
import ru.hse.diploma.util.ConverterService;
import ru.hse.diploma.view.WebPageAnalyseResultViewItem;
import org.springframework.stereotype.Component;

/**
 * Сервис-конвертор доменного объекта в DTO и обратно.
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
@Component
public class WebPageAnalyseResultConverterService implements ConverterService<WebPageAnalyseResult, WebPageAnalyseResultViewItem> {

	/**
	 * Репозиторий для работы с веб-страницами.
	 */
	private final WebPageRepository webPageRepository;

	/**
	 * Конструктор.
	 *
	 * @param webPageRepository
	 */
	public WebPageAnalyseResultConverterService(WebPageRepository webPageRepository) {
		this.webPageRepository = webPageRepository;
	}

	@Override
	public WebPageAnalyseResultViewItem toViewItem(WebPageAnalyseResult webPageAnalyseResult) {
		WebPageAnalyseResultViewItem viewItem = new WebPageAnalyseResultViewItem();
		viewItem.setId(webPageAnalyseResult.getId());
		viewItem.setLemmatization(webPageAnalyseResult.getLemmatization());
		viewItem.setErrorText(webPageAnalyseResult.getErrorText());
		viewItem.setRawText(webPageAnalyseResult.getRawText());
		viewItem.setNormalization(webPageAnalyseResult.getNormalization());
		viewItem.setPhase(webPageAnalyseResult.getPhase());
		WebPage webPage = webPageRepository.findById(webPageAnalyseResult.getWebPageId()).orElseThrow();
		viewItem.setWebPageId(webPageAnalyseResult.getWebPageId());
		viewItem.setWebPageName(webPage.getUrl());
		viewItem.setStartAnalyse(webPageAnalyseResult.getStartAnalyse());
		viewItem.setFinishAnalyse(webPageAnalyseResult.getFinishAnalyse());
		viewItem.setCreationTime(webPageAnalyseResult.getCreationTime());
		viewItem.setLastModifiedTime(webPageAnalyseResult.getLastModifiedTime());
		viewItem.setTs(webPageAnalyseResult.getTs());
		return viewItem;
	}

	@Override
	public WebPageAnalyseResult toDomain(WebPageAnalyseResultViewItem webPageAnalyseResultViewItem) {
		throw new RuntimeException("Not implemented");
	}
}
