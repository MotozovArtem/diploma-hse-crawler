package ru.hse.diploma.view;

import ru.hse.diploma.domain.AnalysePhase;

import java.time.LocalDateTime;
import java.util.List;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
public class WebPageAnalyseResultViewItem {

//	public static class WordCount {
//		public final Map<String, Integer> wordCount;
//
//		public final Integer uniqueWords;
//
//		public WordCount(Map<String, Integer> wordCount, Integer uniqueWords) {
//			this.wordCount = wordCount;
//			this.uniqueWords = uniqueWords;
//		}
//
//		@Override
//		public String toString() {
//			return "WordCount{" +
//					"wordCount=" + wordCount +
//					", uniqueWords=" + uniqueWords +
//					'}';
//		}
//	}
//
//	public static class TokenizationResult {
//		public final Map<String, String> sentenceTokenize;
//
//		public final Map<String, String> wordTokenize;
//
//		public TokenizationResult(Map<String, String> sentenceTokenize, Map<String, String> wordTokenize) {
//			this.sentenceTokenize = sentenceTokenize;
//			this.wordTokenize = wordTokenize;
//		}
//
//		@Override
//		public String toString() {
//			return "TokenizationResult{" +
//					"sentenceTokenize=" + sentenceTokenize +
//					", wordTokenize=" + wordTokenize +
//					'}';
//		}
//	}
//
//	public static class LemmatizationResult {
//		public final Map<String, String> lemmatization;
//
//		public LemmatizationResult(Map<String, String> lemmatization) {
//			this.lemmatization = lemmatization;
//		}
//
//		@Override
//		public String toString() {
//			return "LemmatizationResult{" +
//					"lemmatization=" + lemmatization +
//					'}';
//		}
//	}

	/**
	 * Идентификатор.
	 */
	private String id;
//
//	/**
//	 * Результат лемматизации.
//	 */
//	private TokenizationResult tokenization;
//
//	/**
//	 * Результат лемматизации.
//	 */
//	private LemmatizationResult lemmatization;


	/**
	 * Результат лемматизации.
	 */
	private String tokenization;

	/**
	 * Результат лемматизации.
	 */
	private String lemmatization;

//	/**
//	 * Результат нормализации.
//	 */
//	private List<String> normalization;

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
	 * Результат подсчета слов.
	 */
	private String wordCount;

	/**
	 * Идентификатор веб-страницы.
	 */
	private String webPageId;

	/**
	 * Наименование веб-страницы.
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

//	public TokenizationResult getTokenization() {
//		return tokenization;
//	}
//
//	public void setTokenization(TokenizationResult tokenization) {
//		this.tokenization = tokenization;
//	}
//
//	public LemmatizationResult getLemmatization() {
//		return lemmatization;
//	}
//
//	public void setLemmatization(LemmatizationResult lemmatization) {
//		this.lemmatization = lemmatization;
//	}

	public String getTokenization() {
		return tokenization;
	}

	public void setTokenization(String tokenization) {
		this.tokenization = tokenization;
	}

	public String getLemmatization() {
		return lemmatization;
	}

	public void setLemmatization(String lemmatization) {
		this.lemmatization = lemmatization;
	}

//	public List<String> getNormalization() {
//		return normalization;
//	}
//
//	public void setNormalization(List<String> normalization) {
//		this.normalization = normalization;
//	}


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

//	public WordCount getWordCount() {
//		return wordCount;
//	}
//
//	public void setWordCount(WordCount wordCount) {
//		this.wordCount = wordCount;
//	}

	public String getWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
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
				", tokenization=" + tokenization +
				", lemmatization=" + lemmatization +
				", normalization=" + normalization +
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
