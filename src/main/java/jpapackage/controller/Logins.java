package jpapackage.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jpapackage.Exception.InvalidCredentials;
import jpapackage.Exception.MissingFields;
import jpapackage.dto.ErrorDto;
import jpapackage.dto.logindto;
import jpapackage.dto.tokendto;
import jpapackage.repo.UserRepo;
import jpapackage.service.JWTSERVICE;
import jpapackage.service.LoginsService;

@RestController
@CrossOrigin(origins="http://localhost:3333")
@RequestMapping("/both")
public class Logins {
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private LoginsService service;
	@GetMapping(path="/encodepassword/{password}")
	public String encrypt(@PathVariable String password) {
		return encoder.encode(password);		
	}
	
	@PostMapping(path="/userlogin",consumes=MediaType.APPLICATION_JSON_VALUE)
	public tokendto getuser(@RequestBody logindto save) {
		System.out.println("came into");

		return service.userlogin(save);
		
	}
	@PostMapping(path="/employeelogin",consumes=MediaType.APPLICATION_JSON_VALUE)
	public tokendto getemployee(@RequestBody logindto save) {

		return service.employeelogin(save);
		
	}
	@GetMapping(path="/alluser/{email}/{oldpassword}")
	public boolean checkpassword(@PathVariable String email,@PathVariable String oldpassword) {
		String pass=service.getpassword(email);
		return encoder.matches(oldpassword, pass);
	}



}
