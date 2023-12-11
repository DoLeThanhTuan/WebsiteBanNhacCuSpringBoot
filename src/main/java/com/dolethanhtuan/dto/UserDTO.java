package com.dolethanhtuan.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends GenericDTO{
	private String username;
	private String password;
	private String fullName;
	private String email;
	private Date birthDay;
	private String sex;
	private int status;
	private Set<RoleDTO> roles = new HashSet<>();
}
