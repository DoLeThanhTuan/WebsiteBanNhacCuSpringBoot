package com.dolethanhtuan.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity extends GenericEntity{
	@Column(name="roleCode",nullable = false,length = 100,unique = true)
	private String roleCode;
	@Column(name="roleName", nullable = false,length = 200)
	private String roleName;
	@ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<UserEntity> users = new HashSet<>();
	public RoleEntity(String roleCode, String roleName) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
	}
	
	public RoleEntity(String roleCode, String roleName, Set<UserEntity> users) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.users = users;
	}

	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public RoleEntity() {
		super();
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
	
}
