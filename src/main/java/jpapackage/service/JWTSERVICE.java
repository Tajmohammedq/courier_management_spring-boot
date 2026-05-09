package jpapackage.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTSERVICE {
	boolean isTokenValid(String token,UserDetails userdetails);
	
	String extractusername(String Token);
	String generateToken(UserDetails userdetails);
	
	String RefreshToken(Map<String,Object> extraClaims,UserDetails userdetails);
	

}
