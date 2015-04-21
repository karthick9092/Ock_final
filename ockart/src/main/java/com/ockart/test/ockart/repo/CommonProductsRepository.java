package com.ockart.test.ockart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ockart.entity.common.OckProductCommon;

@Repository
public interface CommonProductsRepository extends JpaRepository<OckProductCommon, Long> {

	OckProductCommon findByItemNumber(String id);

}
