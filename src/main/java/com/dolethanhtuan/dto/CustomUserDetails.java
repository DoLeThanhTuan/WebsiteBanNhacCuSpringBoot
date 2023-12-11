package com.dolethanhtuan.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dolethanhtuan.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	@JsonIgnore
	private String password;
	private String fullName;
	private String sex;
	private Date birthDay;
	private int Status;
	private Collection<? extends GrantedAuthority> listRoles; 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.listRoles;
	}
	public static CustomUserDetails mapToUserDetails(UserEntity userE) {
		List<SimpleGrantedAuthority> roles = userE.getRoles().stream()
				// chuyển đổi từng phần tử ở trong userE.getRoles() thành SimpleGrantedAuthority
				.map(role -> new SimpleGrantedAuthority(role.getRoleCode()))
				// sau đó gom các phần tử đã tạo rồi ép nó thành 1 danh sách rồi gán lại cho roles
				.collect(Collectors.toList());
		// Trả về đối tượng CustomUserDetails
		return new CustomUserDetails(userE.getUsername(), userE.getPassword(),
				userE.getFullName(), userE.getSex(), userE.getBirthDay(), 
				userE.getStatus(), roles);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
