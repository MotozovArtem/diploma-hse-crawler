package ru.hse.diploma.domain;

import java.util.List;

/**
 * Модель Веб-портала.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
public class WebPortal extends DomainObject{
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
}
