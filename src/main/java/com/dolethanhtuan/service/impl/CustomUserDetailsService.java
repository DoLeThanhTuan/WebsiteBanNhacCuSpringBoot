package com.dolethanhtuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.CustomUserDetails;
import com.dolethanhtuan.entity.UserEntity;
import com.dolethanhtuan.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
		@Autowired
		private UserRepository userRepository;
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			UserEntity userE = userRepository.findOneByUsernameAndStatus(username,1);
			if(userE == null)
				throw new UsernameNotFoundException("Not found username");
			return CustomUserDetails.mapToUserDetails(userE);
		}
}
