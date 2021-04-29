package ru.hse.diploma.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

/**
 * Абстрактный класс доменного объекта.
 *
 * @author Artem Motozov
 * @since 2021.04.29
 */
public abstract class DomainObject {
	/**
	 * todo armotozov.
	 */
	@Id
	private String id;

	/**
	 * todo armotozov.
	 */
	@CreatedDate
	private LocalDateTime creationTime;

	/**
	 * todo armotozov.
	 */
	@LastModifiedDate
	private LocalDateTime lastModifiedTime;

	/**
	 * todo armotozov.
	 */
	@Version
	private Long ts;

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
