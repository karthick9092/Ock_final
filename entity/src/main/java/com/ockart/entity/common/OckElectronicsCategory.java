package com.ockart.entity.common;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ock_product_categories database table.
 * 
 */
@Entity
@Table(name="ock_electronics_categories")
public class OckElectronicsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	private long categoryId;

	@Column(name="category_name")
	private String categoryName;

	//bi-directional many-to-one association to OckProduct
	@OneToMany(mappedBy="ockProductCategory")
	private List<OckProductElectronics> ockProducts;

	public OckElectronicsCategory() {
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<OckProductElectronics> getOckProducts() {
		return this.ockProducts;
	}

	public void setOckProducts(List<OckProductElectronics> ockProducts) {
		this.ockProducts = ockProducts;
	}

	public OckProductElectronics addOckProduct(OckProductElectronics ockProduct) {
		getOckProducts().add(ockProduct);
		ockProduct.setOckProductCategory(this);

		return ockProduct;
	}

	public OckProductElectronics removeOckProduct(OckProductElectronics ockProduct) {
		getOckProducts().remove(ockProduct);
		ockProduct.setOckProductCategory(null);

		return ockProduct;
	}
}