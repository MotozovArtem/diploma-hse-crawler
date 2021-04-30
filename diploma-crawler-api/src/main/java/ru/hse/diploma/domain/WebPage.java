package ru.hse.diploma.domain;

/**
 * Модель Веб-страницы.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
public class WebPage extends DomainObject {
	/**
	 * URL веб-страницы.
	 */
	private String url;

	/**
	 * Ресурс веб-страницы.
	 */
	private String resourceName;

	/**
	 * Метаданные веб-страницы.
	 */
	private String meta;

	/**
	 * Заголовок веб-страницы.
	 */
	private String head;

	/**
	 * Веб-портал.
	 */
	private WebPortal webPortal;

	/**
	 * Результаты анализа веб-страницы.
	 */
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

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
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
