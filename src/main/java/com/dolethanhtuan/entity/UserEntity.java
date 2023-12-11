package com.dolethanhtuan.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="[user]")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends GenericEntity{
	@Column(name="username",nullable = false,unique = true)
	private String username;
	@Column(name="password",nullable =  false)
	private String password;
	@Column(name="fullName")
	private String fullName;
	@Column(name="email",nullable = false)
	private String email;
	@Column(name="birthDay")
	private Date birthDay;
	@Column(name="sex")
	private String sex;
	@Column(name="status")
	private int status;
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name= "user_id"),
			inverseJoinColumns =  @JoinColumn(name ="role_id")
			)
	private Set<RoleEntity> roles = new HashSet<>();
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private CartEntity cart;
	public UserEntity(String username, String password, String email, int status) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.status = status;
	}
	
	public UserEntity(String username, String password, String email, int status, Set<RoleEntity> roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.status = status;
		this.roles = roles;
	}


	public UserEntity(String username, String password, String email, Date birthDay, String sex, int status,
			Set<RoleEntity> roles, CartEntity cart) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthDay = birthDay;
		this.sex = sex;
		this.status = status;
		this.roles = roles;
		this.cart = cart;
	}

	public UserEntity(String username, String password, String email, Date birthDay, String sex, int status) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthDay = birthDay;
		this.sex = sex;
		this.status = status;
	}

	
	
	
}
