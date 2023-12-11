package com.dolethanhtuan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.config.jwtProvider;
import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.repository.UserRepository;
import com.dolethanhtuan.service.IUserService;

@RestController
public class UserAPI {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private jwtProvider jwtProvider;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("signup")
	public ResponseEntity<?> signup(@RequestBody UserDTO user,@RequestBody String repeatPassword){
		// Kiểm tra xem xác nhận mật khẩu có đúng không
		if(!user.getPassword().equals(repeatPassword))
			return ResponseEntity.badRequest().body("Repeat Password invalid");
		// kiểm tra xem Email đã tồn tại hay ch
		if(userRepository.existsByEmail(user.getEmail()))
			return ResponseEntity.badRequest().body("Email is exist");
		// Kiểm tra username có tồn tại
		if(userRepository.existsByUsername(user.getUsername()))
			return ResponseEntity.badRequest().body("Username is exist");
		try {
			// Lưu user
			UserDTO userD = userService.save(user);
			return ResponseEntity.ok(userD);
		} catch (Exception e) {
			// Nếu lỗi thì trả về là đăng ký thất bại
			return ResponseEntity.badRequest().body("Fali");
		}
		
	}
	@PostMapping("signin")
	public ResponseEntity<?> signin(@RequestBody UserDTO user){
		Authentication authen = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authen);
		String jwt = jwtProvider.createToken(user.getUsername());
		return ResponseEntity.ok(jwt);
	}
}
