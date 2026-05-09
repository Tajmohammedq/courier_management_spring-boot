package jpapackage.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpapackage.service.JWTSERVICE;
import jpapackage.service.UserService;
import jpapackage.service.Implementation.UserServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTSERVICE jwtservice;
	@Autowired
	private UserService userService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("CAME TO FILTER");
		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt=authHeader.substring(7);
		userEmail=jwtservice.extractusername(jwt);
		if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userdetails=userService.userDetailsService().loadUserByUsername(userEmail);
			if(jwtservice.isTokenValid(jwt, userdetails)) {
				SecurityContext securitycontext=SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userdetails,null, userdetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				securitycontext.setAuthentication(token);
				SecurityContextHolder.setContext(securitycontext);	
			}
		}
		filterChain.doFilter(request, response);

		
		
	}

	

}
