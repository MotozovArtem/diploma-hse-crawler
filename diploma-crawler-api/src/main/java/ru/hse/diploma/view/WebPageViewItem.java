package ru.hse.diploma.view;

import java.time.LocalDateTime;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
public class WebPageViewItem {

	/**
	 * Идентификатор.
	 */
	private String id;

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
	private String metaData;

	/**
	 * Заголовок веб-страницы.
	 */
	private String head;

	/**
	 * HTML страница.
	 */
	private String pageText;

	/**
	 * Веб-портал.
	 */
	private String webPortalId;

	/**
	 * Веб-портал.
	 */
	private String webPortalName;

	/**
	 * Результаты анализа веб-страницы.
	 */
	private String webPageAnalyseResultId;

	/**
	 * Результаты анализа веб-страницы.
	 */
	private String webPageAnalyseResultName;

	/**
	 * Время создания записи.
	 */
	private LocalDateTime creationTime;

	/**
	 * Время последнего изменения записи.
	 */
	private LocalDateTime lastModifiedTime;

	/**
	 * Версия.
	 */
	private Long ts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getWebPortalId() {
		return webPortalId;
	}

	public void setWebPortalId(String webPortalId) {
		this.webPortalId = webPortalId;
	}

	public String getWebPortalName() {
		return webPortalName;
	}

	public void setWebPortalName(String webPortalName) {
		this.webPortalName = webPortalName;
	}

	public String getWebPageAnalyseResultId() {
		return webPageAnalyseResultId;
	}

	public void setWebPageAnalyseResultId(String webPageAnalyseResultId) {
		this.webPageAnalyseResultId = webPageAnalyseResultId;
	}

	public String getWebPageAnalyseResultName() {
		return webPageAnalyseResultName;
	}

	public void setWebPageAnalyseResultName(String webPageAnalyseResultName) {
		this.webPageAnalyseResultName = webPageAnalyseResultName;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	@Override
	public String toString() {
		return "WebPageViewItem{" +
				"id='" + id + '\'' +
				", url='" + url + '\'' +
				", resourceName='" + resourceName + '\'' +
				", metaData='" + metaData + '\'' +
				", head='" + head + '\'' +
				", pageText='" + pageText + '\'' +
				", webPortalId='" + webPortalId + '\'' +
				", webPortalName='" + webPortalName + '\'' +
				", webPageAnalyseResultId='" + webPageAnalyseResultId + '\'' +
				", webPageAnalyseResultName='" + webPageAnalyseResultName + '\'' +
				", creationTime=" + creationTime +
				", lastModifiedTime=" + lastModifiedTime +
				", ts=" + ts +
				'}';
	}
}
