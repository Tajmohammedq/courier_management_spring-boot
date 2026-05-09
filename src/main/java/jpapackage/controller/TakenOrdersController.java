package jpapackage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jpapackage.entity.TakenOrders;
import jpapackage.service.TakenOrdersService;

@RestController
@CrossOrigin(origins="http://localhost:3333")
public class TakenOrdersController {
	@Autowired
	TakenOrdersService service;

	@PostMapping(path="/employee/takenorders",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void AddTakenOrders(@RequestBody List<TakenOrders> orders) {
		service.savedata(orders);

	}
	@GetMapping(path="/employee/getpendingorders/{email}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TakenOrders> getpendingorders(@PathVariable String email){
		List<TakenOrders> order=new ArrayList<>();
		try {
			return service.getdata(email);
		}
		catch(Exception e) {
			e.printStackTrace();
			return order;

		}

	}
	@GetMapping(path="/user/canceltakenorder/{trackingnumber}",produces=MediaType.APPLICATION_JSON_VALUE)
	public void getpendingorders(@PathVariable Long trackingnumber){
		service.deleteorder(trackingnumber);

	}
	@GetMapping(path="employee/takenorderchangestatus/{tracking}/{status}/{date}")
	public void changestatus(@PathVariable Long tracking,@PathVariable String status,@PathVariable String date) {

		service.changestatus(tracking,status,date);

	}
	@GetMapping(path="/employee/employeecompletedorders/{email}")
	public List<TakenOrders> getcompletedorders(@PathVariable String email) {
		System.out.println("hitting");
		List<TakenOrders> list=new ArrayList<>();
		try {
			return service.completedorders(email);
		}
		catch(Exception e) {
			e.printStackTrace();
			return list;
		}

	}





}
