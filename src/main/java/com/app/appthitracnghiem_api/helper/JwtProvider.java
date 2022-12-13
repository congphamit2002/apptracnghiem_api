package com.app.appthitracnghiem_api.helper;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtProvider {
	//Khai báo hàm tạo token
	//Giải mã token
	//Kiểm tra token có phải do hệ thống sinh ra hay không
	
	private String SECRECT_KEY = "YWRtaW5AMTIzNDU2Nzg5MGFkbWluQDEyMzQ1Njc4OTBhZG1pbkAxMjM0NTY3ODkwCg==";
	private long JWT_EXPIRED = 8*60*60*1000;
	private Gson gson = new Gson();
	
	public String generateToken(String data) {
		
		Date now = new Date();
		
		Date expired = new Date(now.getTime() + JWT_EXPIRED);
		
		//String json = gson.toJson(user);
		
		return Jwts.builder()
				.setSubject(data) //Dữ liệu muốn lưu kèm ở token
				.setIssuedAt(now) //thời gian khởi tạo
				.setExpiration(expired) //thời gian hết hạn
				.signWith(SignatureAlgorithm.HS256, SECRECT_KEY) // Thuật toán mã hóa và mã hóa dựa trên Secert key
				.compact();
	}
	
	public String decodeToken(String token) {
		return Jwts.parser()
				.setSigningKey(SECRECT_KEY) //Key max hóa token
				.parseClaimsJws(token)  //truyền tham số token
				.getBody().getSubject(); //lấy giá trị
	}
	
	public boolean validationToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(SECRECT_KEY) //Key max hóa token
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
