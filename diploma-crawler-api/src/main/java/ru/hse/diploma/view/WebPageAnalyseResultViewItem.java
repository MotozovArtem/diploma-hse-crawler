package ru.hse.diploma.view;

import ru.hse.diploma.domain.AnalysePhase;

import java.time.LocalDateTime;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
public class WebPageAnalyseResultViewItem {

	/**
	 * Идентификатор.
	 */
	private String id;

	/**
	 * Результат лемматизации.
	 */
	private String lemmatization;

	/**
	 * Результат нормализации.
	 */
	private String normalization;

	/**
	 * Текст очищенный от тегов.
	 */
	private String rawText;

	/**
	 * Текст ошибки.
	 */
	private String errorText;

	/**
	 * Время начала анализа.
	 */
	private LocalDateTime startAnalyse;

	/**
	 * Время завершения анализа.
	 */
	private LocalDateTime finishAnalyse;

	/**
	 * Фаза анализа.
	 */
	private AnalysePhase phase;

	/**
	 * todo armotozov.
	 */
	private String webPageId;

	/**
	 * todo armotozov.
	 */
	private String webPageName;

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

	public String getLemmatization() {
		return lemmatization;
	}

	public void setLemmatization(String lemmatization) {
		this.lemmatization = lemmatization;
	}

	public String getNormalization() {
		return normalization;
	}

	public void setNormalization(String normalization) {
		this.normalization = normalization;
	}

	public String getRawText() {
		return rawText;
	}

	public void setRawText(String rawText) {
		this.rawText = rawText;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public LocalDateTime getStartAnalyse() {
		return startAnalyse;
	}

	public void setStartAnalyse(LocalDateTime startAnalyse) {
		this.startAnalyse = startAnalyse;
	}

	public LocalDateTime getFinishAnalyse() {
		return finishAnalyse;
	}

	public void setFinishAnalyse(LocalDateTime finishAnalyse) {
		this.finishAnalyse = finishAnalyse;
	}

	public AnalysePhase getPhase() {
		return phase;
	}

	public void setPhase(AnalysePhase phase) {
		this.phase = phase;
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
		return "WebPageAnalyseResultViewItem{" +
				"id='" + id + '\'' +
				", lemmatization='" + lemmatization + '\'' +
				", normalization='" + normalization + '\'' +
				", rawText='" + rawText + '\'' +
				", errorText='" + errorText + '\'' +
				", startAnalyse=" + startAnalyse +
				", finishAnalyse=" + finishAnalyse +
				", phase=" + phase +
				", webPageId='" + webPageId + '\'' +
				", webPageName='" + webPageName + '\'' +
				", creationTime=" + creationTime +
				", lastModifiedTime=" + lastModifiedTime +
				", ts=" + ts +
				'}';
	}
}
