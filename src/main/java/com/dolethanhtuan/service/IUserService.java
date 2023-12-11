package com.dolethanhtuan.service;

import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.UserDTO;

@Service
public interface IUserService {
	UserDTO save(UserDTO userD);

}
