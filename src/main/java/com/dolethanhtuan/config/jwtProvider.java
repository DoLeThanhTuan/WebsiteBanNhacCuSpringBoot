package com.dolethanhtuan.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class jwtProvider {
	// Tạo key để mã hoá chuỗi jwt
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	// set thời gian hết hạn của jwt là 1 ngày tính theo milli second s
	private long jwtExpire = 8600000;
	
	
	// Tạo hàm để tạo chuỗi jwt
	public String createToken(String username) {
		return Jwts.builder()
				// set chủ đề của jwt
				.setSubject(username)
				// set ngày bắt đầu hiệu lực
				.setIssuedAt(new Date())
				// set ngày hết hạn của chuỗi
				.setExpiration(new Date(new Date().getTime()+this.jwtExpire))
				// set chứ kí để mã hoá jwt
				.signWith(key,SignatureAlgorithm.HS512)
				// tạo ra chuỗi jwt
				.compact();
	}
	// lấy chuỗi jwt từ yêu cầu client gửi đến
	public String getJwtFromRequest(HttpServletRequest req) {
		// Lấy chuỗi chứa jwt
		String header = req.getHeader("Authorization");
		// Lấy chuỗi jwt
		if(header != null && header.startsWith("Bearer ")) {
			// trả vê chuỗi jwt
			return header.substring(7);
		}
		return null;
	}
	
	// Kiểm tra chuỗi jwt có hợp lệ hay không
	public boolean validataToken(String token) throws Exception {
		try {
			// chuyển đổi jwt sang các Claims
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);
			// Chuyển đổi thành công -> chuỗi hợp lệ
			return true;
		} catch (MalformedJwtException e) {
			// Định dạng jwt không đúng
			log.error("Invalid JWT Token");
		} catch(ExpiredJwtException e) {
			// Chuỗi JWT hết hạn
			log.error("Expired JWT Token");
		} catch(UnsupportedJwtException e) {
			// Không hỗ trợ JWT
			log.error("Unsupported JWT Token");
		} catch(IllegalArgumentException e) {
			// Chuỗi Claims bị rỗng
			log.error("JWT Claims String Is Empty");
		}
		return false;
	}
	// Lấy username từ chuỗi jwt
	public String getUsernameFormToken(String token) {
		// chuyển đổi chuỗi jwt sang các Claims
		String username = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).
				// Lấy thông tin username từ Claims
				getBody().getSubject();
		return username;
	}
}
