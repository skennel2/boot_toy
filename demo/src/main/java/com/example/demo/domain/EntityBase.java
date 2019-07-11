package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

@MappedSuperclass
abstract public class EntityBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	@NonNull
	private Long id;

	@CreatedDate
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDateTime;

	@LastModifiedDate
	@Column(name = "MODIFIED_DATE")
	private LocalDateTime lastModifiedDateTime;

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBase other = (EntityBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntityBase [id=" + id + ", createdDateTime=" + createdDateTime + ", lastModifiedDateTime="
				+ lastModifiedDateTime + "]";
	}

}
