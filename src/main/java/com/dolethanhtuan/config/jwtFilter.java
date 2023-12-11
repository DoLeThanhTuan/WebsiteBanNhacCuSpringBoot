package com.dolethanhtuan.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dolethanhtuan.service.impl.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtFilter extends OncePerRequestFilter {
	@Autowired
	private jwtProvider jwtProvider;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// Lấy chuỗi jwt từ client gửi về
			String token = jwtProvider.getJwtFromRequest(request);
			// Kiểm tra xem chuỗi jwt có tồn tại hay đã hết hạn chưa
			if(token != null && jwtProvider.validataToken(token)) {
				// Mã hoá chuỗi jwt và lấy username từ chuỗi jwt
				String username = jwtProvider.getUsernameFormToken(token);
				// Load user bằng username đã lấy từ chuỗi jwt
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				// Đưa user vào phiên làm việc
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// Đưa user vào context
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}
		} catch (Exception e) {
			// Nếu không tìm thấy user thì sẽ gửi lỗi không có quyền truy cập
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Can't set user authentication");
		}
		filterChain.doFilter(request, response);
		
	}
	
}
