package com.ockart.test.ockart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ockart.entity.common.OckElectronicsCategory;

@Repository
public interface ElectronicsCtgryRepository extends JpaRepository<OckElectronicsCategory, Long>{

}
