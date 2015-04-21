package com.ockart.test.ockart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ockart.entity.common.OckProductCommon;
import com.ockart.test.ockart.repo.CommonProductsRepository;
import com.ockart.test.ockart.service.CommonCtgryService;

@Service
public class CommonCtgryServiceImpl implements CommonCtgryService {
	
	@Autowired
	CommonProductsRepository commonProductsRepository; 

	@Override
	public void saveOrUpdateProduct(List<OckProductCommon> ockProductCommonList) {
		// TODO Auto-generated method stub
		try {
			commonProductsRepository.save(ockProductCommonList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
