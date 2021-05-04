package ru.hse.diploma.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Абстрактный класс доменного объекта.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
public abstract class DomainObject {
	/**
	 * Идентификатор.
	 */
	@Id
	protected String id;

	/**
	 * Время создания записи.
	 */
	@Field("creation_time")
	@CreatedDate
	protected LocalDateTime creationTime;

	/**
	 * Время последнего изменения записи.
	 */
	@Field("last_modified_time")
	@LastModifiedDate
	protected LocalDateTime lastModifiedTime;

	/**
	 * Версия.
	 */
	@Field("ts")
	@Version
	protected Long ts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
