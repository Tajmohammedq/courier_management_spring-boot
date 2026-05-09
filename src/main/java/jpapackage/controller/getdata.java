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

import jpapackage.entity.SaveOrders;
import jpapackage.service.OrderDataService;

@RestController
@CrossOrigin(origins="http://localhost:3333")
public class getdata {
	public getdata() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private OrderDataService service;
	@GetMapping(path="/user/getorders/{email}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SaveOrders> getdatas(@PathVariable String email) {
		System.out.println("came into conttrolle");
		List<SaveOrders> list=new ArrayList<>();
		try {
			return service.getdata(email);
		}
		catch(Exception e) {
			e.printStackTrace();
			return list;


		}
	}
	@PostMapping(path="/saveorder", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void saveorder(@RequestBody SaveOrders saveorders) {
		service.savedata(saveorders);

	}
	@PostMapping(path="/employee/updateorder", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void saveorders(@RequestBody List<SaveOrders> saveorders) {
		service.updateorder(saveorders);

	}
	@GetMapping(path="/user/remove/{number}")
	public void  deletedata(@PathVariable Long number) {
		service.removedata(number);
	}
	
	@GetMapping(path="/employee/getallorders",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SaveOrders> getdata() {
		List<SaveOrders> availableorders=new ArrayList<>();
		try {
			return service.getalldata();
		}
		catch(Exception e) {
			e.printStackTrace();
			return availableorders;
		}
	}

	@GetMapping(path="employee/allorderschangestatus/{tracking}/{status}/{date}")
	public void changestatus(@PathVariable Long tracking,@PathVariable String status,@PathVariable String date) {
		System.out.println(date);
		service.changestatus(tracking,status,date);

	}
	@GetMapping(path="/user/getcompletedorders/{email}")
	public List<SaveOrders> completedorders(@PathVariable String email) {
		List<SaveOrders> list=new ArrayList<>();
		try {
			return service.completedorders(email);
		}
		catch(Exception e) {
			e.printStackTrace();
			return list;
		}
	}
}
