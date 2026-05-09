package jpapackage.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import jpapackage.Exception.InvalidCredentials;
import jpapackage.Exception.MissingFields;
import jpapackage.dto.logindto;
import jpapackage.dto.tokendto;
import jpapackage.repo.EmployeeRepo;
import jpapackage.repo.UserRepo;
import jpapackage.service.Implementation.UserServiceImplementation;
@Service

public class LoginsService {
	@Autowired
	private JWTSERVICE jwtservice;
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private EmployeeRepo emprepo;
	@Autowired
	private  AuthenticationManager authmanager;
	
	public tokendto userlogin(logindto save) {
		tokendto token=new tokendto();
		try {
			var user=userrepo.findByEmail(save.getUser()).orElseThrow(()->new InvalidCredentials("Invalid mail Id entered"));
			try {
				authmanager.authenticate(new UsernamePasswordAuthenticationToken(save.getUser(),save.getPassword()));
			}
			catch(Exception e) {
				throw new InvalidCredentials("Password not Matched");
			}
			var jwt=jwtservice.generateToken(user);
			var refresh=jwtservice.RefreshToken(new HashMap<>(), user);
			token.setToken(jwt);
			token.setRefresh(refresh);
		}
		catch(Exception e) {
			e.printStackTrace();
			token.setMessage(e.getMessage());
			System.out.println(token);
			
		}
		
		return token;
	}
	
	public tokendto employeelogin(logindto save) {
		tokendto token=new tokendto();
		try {
			var user=emprepo.findByEmail(save.getUser()).orElseThrow(()->new InvalidCredentials("Invalid mail Id entered"));
			try {
				authmanager.authenticate(new UsernamePasswordAuthenticationToken(save.getUser(),save.getPassword()));
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new InvalidCredentials("Password not Matched");
			}
			var jwt=jwtservice.generateToken(user);
			var refresh=jwtservice.RefreshToken(new HashMap<>(), user);
			token.setToken(jwt);
			token.setRefresh(refresh);
		}
		catch(Exception e) {
			e.printStackTrace();
			token.setMessage(e.getMessage());
			System.out.println(token);
			
		}
	
		return token;
		
	}
	public String getpassword(String email) {
		return userrepo.findByEmail(email)
				.map(user -> user.getPassword())
				.orElseGet(() -> emprepo.findByEmail(email)
						.map(employee -> employee.getPassword())
						.orElseThrow(() -> new InvalidCredentials("Invalid mail Id entered")));

	}
		
		
	

}
