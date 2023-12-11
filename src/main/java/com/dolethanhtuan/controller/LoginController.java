package com.dolethanhtuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dolethanhtuan.config.jwtProvider;
import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.repository.UserRepository;
import com.dolethanhtuan.service.IUserService;
@Controller
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private jwtProvider jwtProvider;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserRepository userRepository;
	@GetMapping("signin")
	public String signin() {
		return "signin";
	}
	@PostMapping("signin")
	public String signinWeb(Model model,@ModelAttribute UserDTO user) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.createToken(user.getUsername());
		model.addAttribute("jwt", jwt);
		return "signin";
	}
	@GetMapping("signup")
	public String signupView() {
		return "signup";
	}
	@PostMapping("signup")
	public String signup(@ModelAttribute UserDTO user,@RequestParam("repeatPassword") String repeatPassword) {
		// Kiểm tra xem xác nhận mật khẩu có đúng không
		if(!user.getPassword().equals(repeatPassword))
			return "signin?RepeatPasswordInvalid";
		// kiểm tra xem Email đã tồn tại hay ch
		if(userRepository.existsByEmail(user.getEmail()))
			return "signup?ExistedEmail";
		// Kiểm tra username có tồn tại
		if(userRepository.existsByUsername(user.getUsername()))
			return "signup?ExistedUsername";
		try {
			// Lưu user
			userService.save(user);
		} catch (Exception e) {
			// Nếu lỗi thì trả về là đăng ký thất bại
			return "signup?fail";
		}
		return "signup?success";
	}
	
}
