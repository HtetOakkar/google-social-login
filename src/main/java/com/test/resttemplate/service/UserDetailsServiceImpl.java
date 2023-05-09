package com.test.resttemplate.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.resttemplate.Security.UserPrincipal;
import com.test.resttemplate.model.User;
import com.test.resttemplate.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		User user = userRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new EntityNotFoundException("User with mobile number '" + mobileNumber + "' not found."));
		return UserPrincipal.build(user);
	}

}
