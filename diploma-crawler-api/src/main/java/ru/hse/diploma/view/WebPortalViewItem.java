package ru.hse.diploma.view;

import java.time.LocalDateTime;
import java.util.List;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
public class WebPortalViewItem {

	public static class WebPageIdAndResourceName {

		public final String id;

		public final String name;

		public WebPageIdAndResourceName(String id, String name) {
			this.id = id;
			this.name = name;
		}
	}

	/**
	 * Идентификатор.
	 */
	private String id;

	/**
	 * Наименование веб-портала.
	 */
	private String portalName;

	/**
	 * Доменное имя веб-портала.
	 */
	private String domainName;

	/**
	 * Использованные ключевые слова для поиска веб-портала.
	 */
	private List<String> usedKeywords;

	/**
	 * Список собранных страниц для веб-портала.
	 */
	private List<WebPageIdAndResourceName> webPages;

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

	public String getPortalName() {
		return portalName;
	}

	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public List<String> getUsedKeywords() {
		return usedKeywords;
	}

	public void setUsedKeywords(List<String> usedKeywords) {
		this.usedKeywords = usedKeywords;
	}

	public List<WebPageIdAndResourceName> getWebPages() {
		return webPages;
	}

	public void setWebPages(List<WebPageIdAndResourceName> webPages) {
		this.webPages = webPages;
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
		return "WebPortalViewItem{" +
				"id='" + id + '\'' +
				", portalName='" + portalName + '\'' +
				", domainName='" + domainName + '\'' +
				", usedKeywords=" + usedKeywords +
				", webPageIds=" + webPages +
				", creationTime=" + creationTime +
				", lastModifiedTime=" + lastModifiedTime +
				", ts=" + ts +
				'}';
	}
}
