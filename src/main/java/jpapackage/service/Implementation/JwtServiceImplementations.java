package jpapackage.service.Implementation;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jpapackage.service.JWTSERVICE;

@Service
public class JwtServiceImplementations implements JWTSERVICE {

	public String generateToken(UserDetails userdetails) {
		return Jwts.builder().setSubject(userdetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();		
		
	}
	public Key getKey() {
		byte[] key=Decoders.BASE64.decode("skudhewijdwjqijwwqssdijwoeewodowkdpwidpoediwpedewdewdew");
		return Keys.hmacShaKeyFor(key);		
	}
	public String extractusername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public <T> T extractClaims(String token,Function<Claims, T> claimsResolvers) {
		Claims claims=extractallclaims(token);
		return claimsResolvers.apply(claims);
	}
	public Claims extractallclaims(String token) {
		 Claims claim= Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
		 System.out.println("claim");
		 return claim;
	}
	
	public boolean isTokenValid(String token,UserDetails userdetails) {
		final String username=extractusername(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
				
	}
	public boolean isTokenExpired(String token) {
		return extractClaims(token,Claims::getExpiration).before(new Date());
	}
	public String RefreshToken(Map<String,Object> extraClaims,UserDetails userdetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userdetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24*7))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();		
		
	}

	
}
