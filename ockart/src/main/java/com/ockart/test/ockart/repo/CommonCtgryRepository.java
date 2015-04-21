package com.ockart.test.ockart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ockart.entity.common.OckCommonCategory;

@Repository
public interface CommonCtgryRepository extends
		JpaRepository<OckCommonCategory, Long> {

	OckCommonCategory findByCategoryName(String category);

}
