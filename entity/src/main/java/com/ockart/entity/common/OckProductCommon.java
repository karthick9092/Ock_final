package com.ockart.entity.common;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ock_product_common database table.
 * 
 */
@Entity
@Table(name="ock_product_common")
public class OckProductCommon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	private Long productId;

	private String brand;

	@Lob
	private String description;

	@Column(name="item_number")
	private String itemNumber;

	@Lob
	private String offer;

	private BigDecimal price;

	@Column(name="product_name")
	private String productName;

	@Lob
	private String url;

	//bi-directional many-to-one association to OckCommonCategory
	@ManyToOne
	@JoinColumn(name="category_id")
	private OckCommonCategory ockCommonCategory;

	//bi-directional many-to-one association to OckCompany
	@ManyToOne
	@JoinColumn(name="company_id")
	private OckCompany ockCompany;

	public OckProductCommon() {
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemNumber() {
		return this.itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getOffer() {
		return this.offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public OckCommonCategory getOckCommonCategory() {
		return this.ockCommonCategory;
	}

	public void setOckCommonCategory(OckCommonCategory ockCommonCategory) {
		this.ockCommonCategory = ockCommonCategory;
	}

	public OckCompany getOckCompany() {
		return this.ockCompany;
	}

	public void setOckCompany(OckCompany ockCompany) {
		this.ockCompany = ockCompany;
	}

}