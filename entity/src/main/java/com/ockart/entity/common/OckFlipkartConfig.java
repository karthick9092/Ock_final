package com.ockart.entity.common;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;


/**
 * The persistent class for the ock_flipkart_config database table.
 * 
 */
@Entity
@Table(name="ock_flipkart_config")
public class OckFlipkartConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="flipkart_config_id")
	private String flipkartConfigId;

	@Column(name="affiliate_id")
	private String affiliateId;

	@Column(name="affiliate_token")
	private String affiliateToken;

	@Lob
	@Column(name="base_url")
	private String baseUrl;

	@Column(name="create_by")
	private BigInteger createBy;

	@Column(name="create_time")
	private Timestamp createTime;

	private String status;

	@Column(name="update_by")
	private BigInteger updateBy;

	@Column(name="update_time")
	private Timestamp updateTime;

	private BigInteger version;

	public OckFlipkartConfig() {
	}

	public String getFlipkartConfigId() {
		return this.flipkartConfigId;
	}

	public void setFlipkartConfigId(String flipkartConfigId) {
		this.flipkartConfigId = flipkartConfigId;
	}

	public String getAffiliateId() {
		return this.affiliateId;
	}

	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public String getAffiliateToken() {
		return this.affiliateToken;
	}

	public void setAffiliateToken(String affiliateToken) {
		this.affiliateToken = affiliateToken;
	}

	public String getBaseUrl() {
		return this.baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public BigInteger getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(BigInteger createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigInteger getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(BigInteger updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public BigInteger getVersion() {
		return this.version;
	}

	public void setVersion(BigInteger version) {
		this.version = version;
	}

}