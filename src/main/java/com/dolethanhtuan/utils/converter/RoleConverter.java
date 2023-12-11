package com.dolethanhtuan.utils.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.RoleDTO;
import com.dolethanhtuan.entity.RoleEntity;

@Component
public class RoleConverter {
	public RoleDTO toDTO(RoleEntity roleE) {
		RoleDTO roleDTO= new RoleDTO(roleE.getRoleCode(), roleE.getRoleName());
		roleDTO.setId(roleE.getId());
		return roleDTO;
	}
	public RoleEntity toEntity(RoleDTO roleD) {
		RoleEntity roleE = new RoleEntity();
		if(roleD.getId()!=null)
			roleE.setId(roleD.getId());
		if(roleD.getRoleCode() != null)
			roleE.setRoleCode(roleD.getRoleCode());
		if(roleD.getRoleName() != null)
			roleE.setRoleName(roleD.getRoleName());
		return roleE;
	}
	public RoleEntity toEntity(RoleEntity roleEOld,RoleDTO roleD) {
		if(roleD.getRoleCode() != null)
			roleEOld.setRoleCode(roleD.getRoleCode());
		if(roleD.getRoleName() != null)
			roleEOld.setRoleName(roleD.getRoleName());
		return roleEOld;
	}
	public Set<RoleEntity> toListEntity(Set<RoleDTO> listD){
		Set<RoleEntity> listE = new HashSet<>();
		for (RoleDTO roleDTO : listD) {
			listE.add(this.toEntity(roleDTO));
		}
		return listE;
	}
	public Set<RoleDTO> toListDTO(Set<RoleEntity> set){
		Set<RoleDTO> listD = new HashSet<RoleDTO>();
		for (RoleEntity roleE : set) {
			listD.add(this.toDTO(roleE));
		}
		return listD;
	}
}
