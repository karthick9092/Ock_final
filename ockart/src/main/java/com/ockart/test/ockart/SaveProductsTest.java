package com.ockart.test.ockart;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ockart.entity.common.OckElectronicsCategory;
import com.ockart.entity.common.OckProductCommon;
import com.ockart.test.ockart.service.CommonCtgryService;
import com.ockart.test.ockart.service.CommonProdEntityService;
import com.ockart.test.ockart.service.ElectronicsEntityService;
import com.ockart.util.amazon.FlipkartUtils;
import com.ockart.util.amazon.bean.ProductInfo;
import com.ockart.util.amazon.helper.FlipkartCategoryConstant;


public class SaveProductsTest {
	
	private static Map<String, String> productDirectory;
	
	private static List<ProductInfo> productInfoList = null;
	
	private static OckElectronicsCategory ockElectronicsCategory = null;
	
	static FlipkartUtils flipkartUtils = null;
	
	static ElectronicsEntityService electronicsEntityService =  null;
	
	static CommonProdEntityService commonProdEntityService = null;
	
	static CommonCtgryService commonCtgryService = null;   
	 
	@SuppressWarnings("rawtypes")
	public static void main (String args[]) throws Exception {
		try {
			
			ApplicationContext context = new ClassPathXmlApplicationContext("xml-configuration/spring-hibernate-integration.xml");
			flipkartUtils  = context.getBean(FlipkartUtils.class);
			electronicsEntityService  = context.getBean(ElectronicsEntityService.class);
			commonCtgryService = context.getBean(CommonCtgryService.class);
			commonProdEntityService = context.getBean(CommonProdEntityService.class);
			flipkartUtils.initializeConfigDetails();
			productDirectory = flipkartUtils.getFKProductDirectory();
			if (productDirectory != null && productDirectory.size() != 0) {				
				Iterator itr = productDirectory.entrySet().iterator();
				while (itr.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) itr.next();
					saveProductsInOckart(mapEntry
							.getKey().toString(), mapEntry.getValue()
							.toString());					
				}
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private static void saveProductsInOckart(String category, String url) throws Exception {
		// TODO Auto-generated method stub
		try {
			productInfoList = flipkartUtils.getProductList(category, url);
			
			if (category.equals(FlipkartCategoryConstant.MOBILES)) {
				List<OckProductCommon> ockProductCommonList = commonProdEntityService
						.mapProductInfoToEntity(productInfoList, category);
				commonCtgryService.saveOrUpdateProduct(ockProductCommonList);				
			} if (category.equals(FlipkartCategoryConstant.MOBILES)) {
				ockElectronicsCategory = electronicsEntityService
						.addFkDtlsToProductEntity(productInfoList, category);
				ockElectronicsCategory = electronicsEntityService.addAmazonDtlsToProductEntity(ockElectronicsCategory);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
