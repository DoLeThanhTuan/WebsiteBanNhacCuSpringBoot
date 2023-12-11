package com.dolethanhtuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolethanhtuan.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	RoleEntity findOneById(long id);
	RoleEntity findOneByRoleCode(String roleCode);
	void deleteByRoleCode(String roleCode);
}
