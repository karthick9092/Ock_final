package com.ockart.sample.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.ockart.entity.common.UserAccount;

public class UserRepository {

	@PersistenceContext
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public void insert(UserAccount userAccount) {
		try {
			entityManager.persist(userAccount);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public UserAccount findByUsername(String userName) {
		List<UserAccount> userAccountList = null;
		try {
		userAccountList = entityManager.createQuery("from UserAccount where userName = ?")
					.setParameter(1, userName).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userAccountList.get(0);          
		
	}
}
