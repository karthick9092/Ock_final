package com.ockart.entity.common;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ock_product database table.
 * 
 */
@Entity
@Table(name="ock_product_electronics")
public class OckProductElectronics implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private long productId;

	@Column(name="product_name")
	private String productName;
	
	private String description;
	
	private String manufacturer;

	//bi-directional many-to-one association to OckProductCategory
	@ManyToOne
	@JoinColumn(name="category_id")
	private OckElectronicsCategory ockProductCategory;

	//bi-directional many-to-one association to OckProductDtl
	@OneToMany(mappedBy="ockProduct")
	private List<OckProductDtlElectronics> ockProductDtls;

	public OckProductElectronics() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public OckElectronicsCategory getOckProductCategory() {
		return this.ockProductCategory;
	}

	public void setOckProductCategory(OckElectronicsCategory ockProductCategory) {
		this.ockProductCategory = ockProductCategory;
	}

	public List<OckProductDtlElectronics> getOckProductDtls() {
		return this.ockProductDtls;
	}

	public void setOckProductDtls(List<OckProductDtlElectronics> ockProductDtls) {
		this.ockProductDtls = ockProductDtls;
	}

	public OckProductDtlElectronics addOckProductDtl(OckProductDtlElectronics ockProductDtl) {
		getOckProductDtls().add(ockProductDtl);
		ockProductDtl.setOckProduct(this);

		return ockProductDtl;
	}

	public OckProductDtlElectronics removeOckProductDtl(OckProductDtlElectronics ockProductDtl) {
		getOckProductDtls().remove(ockProductDtl);
		ockProductDtl.setOckProduct(null);

		return ockProductDtl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}