package com.dolethanhtuan.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.RoleDTO;
import com.dolethanhtuan.entity.RoleEntity;
import com.dolethanhtuan.repository.RoleRepository;
import com.dolethanhtuan.service.IRoleService;
import com.dolethanhtuan.utils.converter.RoleConverter;

@Component
public class RoleService implements IRoleService{
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleConverter roleConverter;
	@Override
	public Set<RoleDTO> getAllRole() {
		List<RoleEntity> listE = roleRepository.findAll();
		Set<RoleEntity> setE = new HashSet<>();
		for (RoleEntity roleEntity : listE) {
			setE.add(roleEntity);
		}
		return roleConverter.toListDTO(setE);
	}
	@Override
	public RoleDTO save(RoleDTO roleD) {
		RoleEntity roleE = roleRepository.save(roleConverter.toEntity(roleD));
		return roleConverter.toDTO(roleE);
	}
	@Override
	public RoleDTO update(RoleDTO roleD){
		RoleEntity roleEOld = roleRepository.findOneById(roleD.getId());
		if(roleEOld == null)
			return null;
		roleEOld = roleConverter.toEntity(roleEOld, roleD);
		return roleConverter.toDTO(roleRepository.save(roleEOld));
	}
	@Override
	public void deleteByRoleCode(String roleCode) {
		roleRepository.deleteByRoleCode(roleCode);
	}
}
