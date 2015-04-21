package com.ockart.entity.common;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the ock_amazon_config database table.
 * 
 */
@Entity
@Table(name="ock_amazon_config")
public class OckAmazonConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="amazon_config_id")
	private long amazonConfigId;

	@Column(name="access_key")
	private String accessKey;

	@Column(name="affiliate_id")
	private String affiliateId;

	@Column(name="create_by")
	private Long createBy;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="request_version")
	private String requestVersion;

	private String search_domain_URL;

	@Column(name="secret_key")
	private String secretKey;

	private String service;

	private String status;

	@Column(name="update_by")
	private Long updateBy;

	@Column(name="update_time")
	private Timestamp updateTime;

	private Long version;

	public OckAmazonConfig() {
	}

	public long getAmazonConfigId() {
		return this.amazonConfigId;
	}

	public void setAmazonConfigId(long amazonConfigId) {
		this.amazonConfigId = amazonConfigId;
	}

	public String getAccessKey() {
		return this.accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAffiliateId() {
		return this.affiliateId;
	}

	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRequestVersion() {
		return this.requestVersion;
	}

	public void setRequestVersion(String requestVersion) {
		this.requestVersion = requestVersion;
	}

	public String getSearch_domain_URL() {
		return this.search_domain_URL;
	}

	public void setSearch_domain_URL(String search_domain_URL) {
		this.search_domain_URL = search_domain_URL;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}