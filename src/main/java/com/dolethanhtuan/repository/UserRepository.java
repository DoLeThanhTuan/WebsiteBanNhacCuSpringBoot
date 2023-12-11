package com.dolethanhtuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolethanhtuan.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUsernameAndStatus(String username,int Status);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
