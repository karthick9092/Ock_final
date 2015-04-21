package com.ockart.util.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ockart.entity.common.OckElectronicsCategory;

@Repository
public interface OckElectronicsCtgryRepository extends JpaRepository<OckElectronicsCategory, Long>{

	OckElectronicsCategory findByCategoryName(String mobiles);

}
