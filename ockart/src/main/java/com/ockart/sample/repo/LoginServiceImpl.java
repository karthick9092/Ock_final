package com.ockart.sample.repo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.ockart.entity.common.UserAccount;
import com.ockart.entity.common.UserRole;

public class LoginServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
   
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserAccount userAccount = null;
		List<GrantedAuthority> authorities = null;
		try {
			userAccount = userRepository.findByUsername(username);
			authorities = buildUserAuthority(userAccount.getUserRoles());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new User(userAccount.getUserName(), userAccount.getPassword(), authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(List<UserRole> list) {
		 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
		// Build user's authorities
		for (UserRole userRole : list) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getUserRole()));
		}
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}

}
