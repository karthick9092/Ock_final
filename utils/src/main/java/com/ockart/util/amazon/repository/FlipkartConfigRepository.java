package com.ockart.util.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ockart.entity.common.OckFlipkartConfig;

@Repository
public interface FlipkartConfigRepository extends JpaRepository<OckFlipkartConfig, Long>{

	OckFlipkartConfig findByStatus(String active);
}
