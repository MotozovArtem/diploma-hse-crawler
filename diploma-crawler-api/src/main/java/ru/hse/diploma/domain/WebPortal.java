package ru.hse.diploma.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Модель Веб-портала.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
@Document(collection = "web_portals")
public class WebPortal extends DomainObject {
	/**
	 * Наименование веб-портала.
	 */
	@Field("portal_name")
	private String portalName;

	/**
	 * Доменное имя веб-портала.
	 */
	@Field("domain_name")
	private String domainName;

	/**
	 * Использованные ключевые слова для поиска веб-портала.
	 */
	@Field("used_keywords")
	private List<String> usedKeywords;

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

	@Override
	public String toString() {
		return "WebPortal{" +
				"id='" + id + '\'' +
				", portalName='" + portalName + '\'' +
				", domainName='" + domainName + '\'' +
				", usedKeywords=" + usedKeywords +
				", creationTime=" + creationTime +
				", lastModifiedTime=" + lastModifiedTime +
				", ts=" + ts +
				'}';
	}
}
