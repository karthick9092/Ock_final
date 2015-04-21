package com.ockart.sample.action;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ockart.entity.amazon.com.ecs.client.jax.AWSECommerceService;
import com.ockart.entity.amazon.com.ecs.client.jax.ItemSearch;
import com.ockart.entity.amazon.com.ecs.client.jax.ItemSearchResponse;
import com.ockart.sample.bean.ProductBean;
import com.ockart.sample.service.ProductService;

@Controller
public class ProductResource {
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	@ResponseBody
	public String retrieveProducts() {
		List<ProductBean> productList = null;
		String productJson = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			productList = productService.retrieveAll();
			productJson = mapper.writeValueAsString(productList);
			ItemSearch searchRequest = new ItemSearch();
			AWSECommerceService awseCommerceService = new AWSECommerceService();
			ItemSearchResponse itemSearchResponse =awseCommerceService.getAWSECommerceServicePortIN().itemSearch(searchRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productJson;
	}
}
