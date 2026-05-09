package jpapackage.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jpapackage.Exception.InvalidTrackingNumber;
import jpapackage.Exception.NoTakenOrders;
import jpapackage.entity.TakenOrders;
import jpapackage.repo.TakenOrdersRepo;
import jpapackage.service.TakenOrdersService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class TakenOrdersServiceTest {
	@Tested
	TakenOrdersService service;
	@Injectable
	TakenOrdersRepo repo;
	
	
	@Test
	public void savedata() {
		
		List<TakenOrders> orders=new ArrayList<>();
		TakenOrders singleorder=new TakenOrders();
		orders.add(singleorder);
		service.savedata(orders);
		new Verifications(){{
			repo.save(singleorder);
			
		}};

	}
	@Test
	public void getdata(){
		
		List<TakenOrders> orders=new ArrayList<>();
		try {
			new Expectations(){{
				repo.findAll();
				result=orders;
				
			}};
			service.getdata("new@gmail.com");
		}
		catch(Exception e) {
			assertEquals("Employee does not have any Completed orders",e.getMessage());
		}
	}
	@Test
	public void deleteorder() {
		Long num=(long) 2323;
		service.deleteorder(num);
		new Verifications(){{
			repo.deleteById(num);
		}};
	}
	@Test
	public void changestatus() {
		Long num=(long) 2323;
		try {
			service.changestatus(num, "status", "date");;
			new Verifications(){{
				repo.getById(num);
			}};
		}
		catch(Exception e) {
			assertEquals("Invalid Tracking number Entered",e.getMessage());
			
		}
		

	}
	
	@Test
	public void completedorders() {
		List<TakenOrders> taken=new ArrayList<>();
		try {
			new Expectations() {{
				repo.findAll();
				result=taken;
				
				
			}};
			assertEquals(taken,service.completedorders("new@gmail.com"));
		}
		catch(Exception e) {
			assertEquals("Employee doesnt have any completed orders",e.getMessage());
			
		}
	}
			


}
