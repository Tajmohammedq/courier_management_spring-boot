package jpapackage.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jpapackage.controller.TakenOrdersController;
import jpapackage.entity.TakenOrders;
import jpapackage.service.TakenOrdersService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class TakenOrdersControllerTest {
	@Tested
	TakenOrdersController controller;
	@Injectable
	TakenOrdersService service;
	@Test
	public void AddTakenOrders() {
		List<TakenOrders> completedorders=new ArrayList<>();
		controller.AddTakenOrders(completedorders);
		  new Verifications() {{
	            service.savedata(completedorders);
	        }};
		

	}
	@Test
	public void getpendingorders(){
		List<TakenOrders> order=new ArrayList<>();
		new Expectations() {{
			service.getdata("test@gmail.com");
			result=order;		
		}};
		assertEquals(order,controller.getpendingorders("test@gmail.com"));
	}
	
	@Test
	public void getpendingorder(){
		Long num=(long) 2323;
		controller.getpendingorders(num);
		  new Verifications() {{
	            service.deleteorder(num);
	        }};
	}
	@Test
	public void changestatus() {
		Long num=(long) 2323;
		controller.changestatus(num,"status","date");
		  new Verifications() {{
	            service.changestatus(num,"status","date");
	        }};
	}
	@Test
	public void getcompletedorders() {
		
		List<TakenOrders> Takenlist=new ArrayList<>();
		new Expectations() {{
			service.completedorders("test@gmail.com");
			result=Takenlist;		
		}};
		assertEquals(Takenlist,controller.getcompletedorders("test@gmail.com"));


	}
}
