package ru.hse.diploma.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Модель результатов анализа Веб-страницы.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
@Document(collection = "domain_object")
public class WebPageAnalyseResult extends DomainObject {

	/**
	 * Результат лемматизации.
	 */
	@Field("lemmatization")
	private String lemmatization;

	/**
	 * Результат нормализации.
	 */
	@Field("normalization")
	private String normalization;

	/**
	 * Текст очищенный от тегов.
	 */
	@Field("raw_text")
	private String rawText;

	/**
	 * Текст ошибки.
	 */
	@Field("error_text")
	private String errorText;

	/**
	 * Время начала анализа.
	 */
	@Field("start_analyse")
	private LocalDateTime startAnalyse;

	/**
	 * Время завершения анализа.
	 */
	@Field("finish_analyse")
	private LocalDateTime finishAnalyse;

	/**
	 * Фаза анализа.
	 */
	@Field("phase")
	private AnalysePhase phase;

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
}
