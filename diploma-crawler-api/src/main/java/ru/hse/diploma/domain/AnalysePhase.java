package ru.hse.diploma.domain;

/**
 * Перечисление этапов анализа страницы.
 *
 * @author Artem Motozov
 * @since 2021.05.01
 */
public enum AnalysePhase {
	/**
	 * Анализ начат.
	 */
	ANALYSE_START("Анализ начат"),
	/**
	 * Анализ закончен.
	 */
	ANALYSE_FINISHED("Анализ закончен"),
	/**
	 * Стадия лемматизации.
	 */
	LEMMATIZATION("Стадия лемматизации"),
	/**
	 * Стадия нормализации.
	 */
	NORMALIZATION("Стадия нормализации"),
	/**
	 * Ошибка анализа.
	 */
	ANALYSE_FAILED("Ошибка анализа");

	private final String phaseName;

	AnalysePhase(final String phaseName) {
		this.phaseName = phaseName;
	}

	public String getPhaseName() {
		return phaseName;
	}
}
