package com.app.appthitracnghiem_api.helper;

import com.app.appthitracnghiem_api.security.CustomUserDetailService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = getJwtToken(request);
		if(jwtProvider.validationToken(token)) {
			//token hợp lệ
			String jsonData = jwtProvider.decodeToken(token);
			System.out.println("Kiem tra data token " + jsonData);
			Gson gson = new Gson();
			//User user = gson.fromJson(jsonData, User.class);
			User userDetail = (User) customUserDetailService.loadUserByUsername(jsonData);
			
			UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
			
			 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		} else {
			//token kh hop le
			System.out.println("Auth : Login failed");
		}

		filterChain.doFilter(request, response);
	}
	
	private String getJwtToken(HttpServletRequest request) {
		String authenToken =  request.getHeader("Authorization");
		
		if(StringUtils.hasText(authenToken) && authenToken.contains("Bearer")) {
			String token = authenToken.substring(7);
			System.out.println("Kiem tra tokeb Bearer " + token);//loại bỏ bearer và lấy chữ đằng sau bearer
			return token;
		}
		return null;
	}
}
