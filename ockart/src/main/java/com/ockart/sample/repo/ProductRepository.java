package com.ockart.sample.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ockart.entity.common.Product;

public class ProductRepository {
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		try {
			String sql = "select c from Product as c";
			productList = em.createQuery(sql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}	
}
