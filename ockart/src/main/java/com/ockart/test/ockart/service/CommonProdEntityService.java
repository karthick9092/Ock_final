package com.ockart.test.ockart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ockart.entity.common.OckCommonCategory;
import com.ockart.entity.common.OckCompany;
import com.ockart.entity.common.OckProductCommon;
import com.ockart.test.ockart.repo.CommonCtgryRepository;
import com.ockart.test.ockart.repo.CommonProductsRepository;
import com.ockart.util.amazon.bean.ProductInfo;
import com.ockart.util.amazon.helper.CommonConstants;
import com.ockart.util.amazon.repository.CompanyRepository;

@Service
public class CommonProdEntityService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CommonCtgryRepository commonCtgryRepository;
	
	@Autowired
	CommonProductsRepository commonProductsRepository;
	
	/***
	 * Mapping product other than electronics to entity
	 * @param productInfoList
	 * @param category
	 * @return
	 */

	public List<OckProductCommon> mapProductInfoToEntity(
			List<ProductInfo> productInfoList, String category) {
		// TODO Auto-generated method stub
		OckProductCommon ockProductCommon = null;
		OckCompany ockCompany = null;
		OckCommonCategory ockCommonCategory = null;
		List<OckProductCommon> ockProductCommonList = null;
		try {
			if (productInfoList != null && productInfoList.size() != 0) {
				ockProductCommonList = new ArrayList<OckProductCommon>();
				ockCompany = companyRepository
						.findByCompanyName(CommonConstants.FLIPKART);
				ockCommonCategory = commonCtgryRepository.findByCategoryName(category);  
				
				for (int i = 0; i < productInfoList.size(); i++) {					
					ProductInfo productInfo = productInfoList.get(i);
					
					ockProductCommon = commonProductsRepository.findByItemNumber(productInfo.getId());
					if (ockProductCommon == null) {
						ockProductCommon = new OckProductCommon();
					}					
					ockProductCommon.setProductName(productInfo.getTitle());
										
					// Getting The Manufacturer From The Title
					String title = productInfo.getTitle();
					String arr[] = title.split(" ", 2);
					String brand = arr[0];
	
					ockProductCommon.setBrand(brand);
					ockProductCommon.setDescription(productInfo.getDescription());					
					ockProductCommon.setItemNumber(productInfo.getId());
					ockProductCommon.setPrice(new BigDecimal(productInfo
							.getSellingPrice()));
					ockProductCommon.setUrl(productInfo.getProductUrl());
					ockProductCommon.setOckCompany(ockCompany);
					ockProductCommon.setOckCommonCategory(ockCommonCategory);
					ockProductCommonList.add(ockProductCommon);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ockProductCommonList;
	}
}
