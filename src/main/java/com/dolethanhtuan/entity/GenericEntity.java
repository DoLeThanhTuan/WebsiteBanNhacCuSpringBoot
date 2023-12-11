package com.dolethanhtuan.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class GenericEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Column(name="createBy")
	@CreatedBy
	protected String createBy;
	@Column(name="createDate")
	@CreatedDate
	protected Date createDate;
	@Column(name="modifiedBy")
	@LastModifiedBy
	protected String modifiedBy;
	@Column(name="modifiedDate")
	@LastModifiedDate
	protected Date modifiedDate;
}
