package com.dolethanhtuan.utils.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.entity.UserEntity;
@Component
public class UserConverter {
	@Autowired
	private RoleConverter roleConverter;
	public UserDTO toDTO(UserEntity userE) {
		UserDTO userD = new UserDTO();
		userD.setId(userE.getId());
		userD.setUsername(userE.getUsername());
		userD.setPassword(userE.getPassword());
		userD.setFullName(userE.getFullName());
		userD.setEmail(userE.getEmail());
		userD.setBirthDay(userE.getBirthDay());
		userD.setSex(userE.getSex());
		userD.setStatus(userE.getStatus());
		userD.setRoles(roleConverter.toListDTO(userE.getRoles()));
		return userD;
	}
	public UserEntity toEntity(UserDTO userD) {
		UserEntity userE = new UserEntity();
		userE.setId(userD.getId());
		userE.setUsername(userD.getUsername());
		userE.setPassword(userD.getPassword());
		userE.setFullName(userD.getFullName());
		userE.setEmail(userD.getEmail());
		userE.setBirthDay(userD.getBirthDay());
		userE.setSex(userD.getSex());
		userE.setStatus(userD.getStatus());
		userE.setRoles(roleConverter.toListEntity(userD.getRoles()));
		return userE;
	}
}
