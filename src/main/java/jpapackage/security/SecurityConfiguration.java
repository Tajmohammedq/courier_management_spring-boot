package jpapackage.security;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jpapackage.entity.Role;
import jpapackage.service.UserService;
import jpapackage.service.Implementation.UserServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
	@Autowired
	private JwtAuthenticationFilter jwtfilter;
	
	@Autowired
	private UserService userservice;
	
	
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{
		System.out.println("caem to securuty");
		 http.csrf(AbstractHttpConfigurer::disable)
		 	.authorizeHttpRequests(request->request
		 	.requestMatchers("/both/**")
		 	.permitAll()
		 	.requestMatchers("/user/**").hasAuthority(Role.USER.name())
		 	.requestMatchers("/employee/**").hasAuthority(Role.EMPLOYEE.name())
		 	.anyRequest().authenticated())
		 	
		 	.sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authprovider()).addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
			return http.build();
	}
		 
	 @Bean
	 public AuthenticationProvider authprovider() {
		 DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		 provider.setUserDetailsService(userservice.userDetailsService());
		 provider.setPasswordEncoder(passwordEncoder());
		 return provider;		 
	 }

	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
		
	 @Bean
	 public AuthenticationManager authenticationmanager(AuthenticationConfiguration config) throws Exception{
		 return config.getAuthenticationManager();
	}
	 
}
			
		 	 
		 	

		
	
	
	
	


