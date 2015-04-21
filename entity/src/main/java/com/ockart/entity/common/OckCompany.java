package com.ockart.entity.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * The persistent class for the ock_company database table.
 * 
 */
@Entity
@Table(name="ock_company")
public class OckCompany implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="company_id")
	private long companyId;

	@Column(name="company_name")
	private String companyName;

	@Column(name="create_by")
	private Long createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="update_by")
	private Long updateBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Version
	private Long version;

	//bi-directional many-to-one association to OckProductDtl
	@OneToMany(mappedBy="ockCompany")
	private List<OckProductDtlElectronics> ockProductDtls;

	public OckCompany() {
	}

	public long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<OckProductDtlElectronics> getOckProductDtls() {
		return this.ockProductDtls;
	}

	public void setOckProductDtls(List<OckProductDtlElectronics> ockProductDtls) {
		this.ockProductDtls = ockProductDtls;
	}

	public OckProductDtlElectronics addOckProductDtl(OckProductDtlElectronics ockProductDtl) {
		getOckProductDtls().add(ockProductDtl);
		ockProductDtl.setOckCompany(this);

		return ockProductDtl;
	}

	public OckProductDtlElectronics removeOckProductDtl(OckProductDtlElectronics ockProductDtl) {
		getOckProductDtls().remove(ockProductDtl);
		ockProductDtl.setOckCompany(null);

		return ockProductDtl;
	}

}