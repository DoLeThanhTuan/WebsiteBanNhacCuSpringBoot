package com.dolethanhtuan.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.RoleDTO;

@Service
public interface IRoleService {
	public Set<RoleDTO> getAllRole();
	public RoleDTO save(RoleDTO roleD);
	public RoleDTO update(RoleDTO roleD);
	public void deleteByRoleCode(String roleCode);
}
