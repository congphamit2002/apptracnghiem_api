package com.app.appthitracnghiem_api.controller;


import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.helper.JwtProvider;
import com.app.appthitracnghiem_api.payload.LoginRequest;
import com.app.appthitracnghiem_api.payload.LoginRespone;
import com.app.appthitracnghiem_api.service.AccountServiceImp;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class LoginController {
	
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	AccountServiceImp accountServiceImp;
	
	private Gson gson = new Gson(); 
	
	@PostMapping("/login")
	public ResponseEntity<? > login(@RequestBody LoginRequest loginRequest){
		System.out.println("USer name " + loginRequest.getUsername());
		Gson gson = new Gson();
		String json = gson.toJson(loginRequest);
//        logger.info("[IN-REQUEST] " + json);

		//Hàm dùng để kích hoạt đăng nhập bằng tay
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
						, loginRequest.getPassword()) );

		SecurityContextHolder.getContext().setAuthentication(authentication);

		//String jwtToken = jwtProvider.generateToken((User) authentication.getPrincipal());
		String jwtToken = jwtProvider.generateToken(loginRequest.getUsername());
		Accounts accounts = accountServiceImp.getAccountByUsername(loginRequest.getUsername());
		LoginRespone loginRespone = new LoginRespone();
		loginRespone.setUsername(accounts.getUsername());
		loginRespone.setId(accounts.getId());
		loginRespone.setEmail(accounts.getEmail());
		loginRespone.setToken(jwtToken);

		return new ResponseEntity<LoginRespone>(loginRespone, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String test() {
		return "Test";
	}
}
