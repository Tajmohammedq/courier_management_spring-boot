package jpapackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jpapackage.entity.CancelledOrders;
import jpapackage.service.CancelledOrdersService;

@RestController
@CrossOrigin(origins="http://localhost:3333")
public class CancelledordersController {
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	CancelledOrdersService service;
	@PostMapping(path="/user/delete",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody CancelledOrders orders) {
		service.AddToCancelOrder(orders);

	}
	
	@GetMapping(path="/encodepassword/{password}")
	public String encrypt(@PathVariable String password) {
	
		return encoder.encode(password);		
	}
	
	
	
}
