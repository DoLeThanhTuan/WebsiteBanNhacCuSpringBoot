package com.dolethanhtuan.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.entity.RoleEntity;
import com.dolethanhtuan.entity.UserEntity;
import com.dolethanhtuan.repository.RoleRepository;
import com.dolethanhtuan.repository.UserRepository;
import com.dolethanhtuan.service.IUserService;
import com.dolethanhtuan.utils.converter.UserConverter;

@Component
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encode;
	@Override
	public UserDTO save(UserDTO userD) {
		// chuyển đổi từ userDTO mà client truyền về thành userEntity
		UserEntity userE = userConverter.toEntity(userD);
		// Kiểm tra và phân quyền
		// Nếu danh sách Role truyền về là rỗng thì sẽ cho là ROLE_USER
		if(userE.getRoles().size() == 0) {
			Set<RoleEntity> roles= new HashSet<>();
			// Tìm roleEntity theo roleCode là ROLE_USER
			roles.add(roleRepository.findOneByRoleCode("ROLE_USER"));
			userE.setRoles(roles);
		}
		// Gán trạng thái còn hoạt động
		userE.setStatus(1);
		// Mã hoá mật khẩu trước khi lưu
		userE.setPassword(encode.encode(userD.getPassword()));
		// Trả lại client là 1 UserDTO
		return userConverter.toDTO(userRepository.save(userE));
	}
	
}
