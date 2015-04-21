package com.ockart.sample.service;

import java.util.List;

import com.ockart.sample.bean.ProductBean;

public interface ProductService {
	
	List<ProductBean> retrieveAll();
}
