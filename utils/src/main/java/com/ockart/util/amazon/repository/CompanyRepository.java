package com.ockart.util.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ockart.entity.common.OckCompany;

@Repository
public interface CompanyRepository extends JpaRepository<OckCompany, Long>{

	OckCompany findByCompanyName(String flipkart);

}
