package com.dolethanhtuan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dolethanhtuan.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Bean
	public jwtFilter jwtTokenFilter() {
		return new jwtFilter();
	}
	
	// Phân quyền
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Tắt chặn chống tấn công
		http.csrf(csrf -> csrf.disable());
		// Phân quyền đường dẫn
		http.authorizeHttpRequests(author -> author
				.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).authenticated()
				.anyRequest().permitAll()
				);
		// Thiết lập form login và page denied
		http.formLogin(form -> form
				.loginPage("/signin?accessDenied")
				.loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/home")
				.failureUrl("/signin?fail")
				.usernameParameter("username")
				.passwordParameter("password")
				);
		http.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	// Thiết lập userDetailsService cho user
	@Bean
	public UserDetailsService userDetailsService(AuthenticationManagerBuilder auth) throws Exception{
		return customUserDetailsService;
	}
	// Thiết lập xác thực user, thiết lập mã hoá password
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	// Thiết lập mã hoá password
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Sử dụng thuật toán BCrypt để mã hoá password
		return new BCryptPasswordEncoder();
	}
	@Bean()
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
}
