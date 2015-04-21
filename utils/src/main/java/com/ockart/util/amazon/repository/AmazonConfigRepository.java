package com.ockart.util.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ockart.entity.common.OckAmazonConfig;

@Transactional
@Repository
public interface AmazonConfigRepository extends JpaRepository<OckAmazonConfig, Long>{

	OckAmazonConfig findByStatus(String active);

}
