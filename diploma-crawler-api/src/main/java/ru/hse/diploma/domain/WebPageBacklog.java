package ru.hse.diploma.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Денормализованная сущность для регистрирования ошибок сбора данных.
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
@Document(collection = "web_page_backlogs")
public class WebPageBacklog {

	@Id
	private String id;

	@Field("web_page_id")
	private String webPageId;

	@Field("web_page_name")
	private String webPageName;

	@Field("web_portal_id")
	private String webPortalId;

	@Field("web_portal_name")
	private String webPortalName;

	@Field("error_text")
	private String errorText;

	@Field("error_code")
	private String errorCode;

	@Field("creation_time")
	private LocalDateTime creationTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWebPageId() {
		return webPageId;
	}

	public void setWebPageId(String webPageId) {
		this.webPageId = webPageId;
	}

	public String getWebPageName() {
		return webPageName;
	}

	public void setWebPageName(String webPageName) {
		this.webPageName = webPageName;
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

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "WebPageBacklog{" +
				"id='" + id + '\'' +
				", webPageId='" + webPageId + '\'' +
				", webPageName='" + webPageName + '\'' +
				", webPortalId='" + webPortalId + '\'' +
				", webPortalName='" + webPortalName + '\'' +
				", errorText='" + errorText + '\'' +
				", errorCode='" + errorCode + '\'' +
				", creationTime=" + creationTime +
				'}';
	}
}
