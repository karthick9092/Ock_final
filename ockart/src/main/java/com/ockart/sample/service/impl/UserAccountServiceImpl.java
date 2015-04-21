package com.ockart.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ockart.entity.common.UserAccount;
import com.ockart.sample.repo.UserRepository;
import com.ockart.sample.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	UserRepository userRepository;

	
	@Override
	public void save(UserAccount userAccount) {
		// TODO Auto-generated method stub
		try {
			userRepository.insert(userAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
