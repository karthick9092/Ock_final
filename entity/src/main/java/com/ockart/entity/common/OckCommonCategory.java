package com.ockart.entity.common;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ock_common_categories database table.
 * 
 */
@Entity
@Table(name="ock_common_categories")
public class OckCommonCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	private long categoryId;

	@Column(name="category_name")
	private String categoryName;

	//bi-directional many-to-one association to OckProductCommon
	@OneToMany(mappedBy="ockCommonCategory")
	private List<OckProductCommon> ockProductCommons;

	public OckCommonCategory() {
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

	public List<OckProductCommon> getOckProductCommons() {
		return this.ockProductCommons;
	}

	public void setOckProductCommons(List<OckProductCommon> ockProductCommons) {
		this.ockProductCommons = ockProductCommons;
	}

	public OckProductCommon addOckProductCommon(OckProductCommon ockProductCommon) {
		getOckProductCommons().add(ockProductCommon);
		ockProductCommon.setOckCommonCategory(this);

		return ockProductCommon;
	}

	public OckProductCommon removeOckProductCommon(OckProductCommon ockProductCommon) {
		getOckProductCommons().remove(ockProductCommon);
		ockProductCommon.setOckCommonCategory(null);

		return ockProductCommon;
	}

}