package com.dolethanhtuan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleDTO extends GenericDTO{
	private String roleCode;
	private String roleName;
}
