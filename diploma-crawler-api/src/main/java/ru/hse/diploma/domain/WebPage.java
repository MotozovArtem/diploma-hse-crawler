package ru.hse.diploma.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Модель Веб-страницы.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
@Document(collection = "domain_object")
public class WebPage extends DomainObject {
	/**
	 * URL веб-страницы.
	 */
	@Field("url")
	private String url;

	/**
	 * Ресурс веб-страницы.
	 */
	@Field("resource_name")
	private String resourceName;

	/**
	 * Метаданные веб-страницы.
	 */
	@Field("meta_data")
	private String metaData;

	/**
	 * Заголовок веб-страницы.
	 */
	@Field("head")
	private String head;

	/**
	 * HTML страница.
	 */
	@Field("page_text")
	private String pageText;

	/**
	 * Веб-портал.
	 */
	@Field("web_portal_id")
	private WebPortal webPortal;


	/**
	 * Результаты анализа веб-страницы.
	 */
	@Field("web_page_analyse_result_id")
	private WebPageAnalyseResult webPageAnalyseResult;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getPageText() {
		return pageText;
	}

	public void setPageText(String pageText) {
		this.pageText = pageText;
	}

	public WebPortal getWebPortal() {
		return webPortal;
	}

	public void setWebPortal(WebPortal webPortal) {
		this.webPortal = webPortal;
	}

	public WebPageAnalyseResult getWebPageAnalyseResult() {
		return webPageAnalyseResult;
	}

	public void setWebPageAnalyseResult(WebPageAnalyseResult webPageAnalyseResult) {
		this.webPageAnalyseResult = webPageAnalyseResult;
	}
}
