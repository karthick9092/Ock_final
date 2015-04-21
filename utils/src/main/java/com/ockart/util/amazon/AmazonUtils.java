package com.ockart.util.amazon;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ockart.entity.amazon.com.ecs.client.jax.ItemSearchResponse;
import com.ockart.entity.common.OckAmazonConfig;
import com.ockart.entity.common.OckElectronicsCategory;
import com.ockart.util.amazon.helper.SignedRequestsHelper;
import com.ockart.util.amazon.repository.AmazonConfigRepository;
import com.ockart.util.amazon.rest.AmazonRestUtils;


@Controller
public class AmazonUtils {
	
	Map<String, String> requestParameterHashmap = new HashMap<String, String>();
	
	public static final String ACTIVE = "A";
	
	@Autowired
	SignedRequestsHelper signedRequestsHelper;
	
	@Autowired
	AmazonRestUtils restCallUtils; 
	
	@Autowired
	AmazonConfigRepository amazonConfigRepository;
	 
	public ItemSearchResponse itemSearch(String category, String keyword) {
		ItemSearchResponse itemSearchResponse = null;		
		String requestURL = null;
		try {
			OckAmazonConfig amazonConfig = amazonConfigRepository.findByStatus(ACTIVE);		
			try {
				signedRequestsHelper = SignedRequestsHelper.getInstance(
						amazonConfig.getSearch_domain_URL(),
						amazonConfig.getAccessKey(),
						amazonConfig.getSecretKey());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			requestURL = signedRequestsHelper.prepareRequestURL(requestParameterHashmap, amazonConfig, keyword, category);
			itemSearchResponse = restCallUtils.getAmazonResponse(requestURL);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return itemSearchResponse;
	}
	
}
