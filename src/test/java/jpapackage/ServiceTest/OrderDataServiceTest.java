package jpapackage.ServiceTest;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jpapackage.Exception.NoOrders;
import jpapackage.entity.SaveOrders;
import jpapackage.repo.ordertable;
import jpapackage.service.OrderDataService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;



public class OrderDataServiceTest {
	@Tested
	OrderDataService service;
	@Injectable
	ordertable repo;
	
	@Test
	public void getdata(){
		List<SaveOrders> order=new ArrayList<>();
		new Expectations() {{
			repo.findAll();
			result=order;
			
		}};
		 try {
	            service.getdata("test@gmail.com");
	            assertEquals(order, service.getdata("test@gmail.com"));
	        } 
		 catch (Exception e) {
	            assertEquals("USER HAS NO ORDERS", e.getMessage());
	        }
			
		
	}

	@Test
	public void savedata() {
		List<SaveOrders> order=new ArrayList<>();
		SaveOrders saveorder=new SaveOrders();
		 saveorder.setEmail("test@gmail.com");
	     saveorder.setItem("Item");
	     
	     try {
	    	 service.savedata(saveorder); 
	    	 new Verifications() {{
					repo.save(saveorder);
					
				}};
	    	 
	     }
	     catch(Exception e){
	    	 assertEquals("Mandatory fields are missing", e.getMessage());
	    	 
	     }
	        
			
	}
	
		   
	@Test
	public void removedata() {
		Long num=(long) 2344;
		try {
			service.removedata(num);
			new Verifications(){{
				repo.deleteById(num);
				
			}};
		}
		catch(Exception e){
			assertEquals("No order present with this tracking number", e.getMessage());
			
			
		}
	}
	@Test
	public void getalldata() {
		List<SaveOrders> available=new ArrayList<>();
		
	
		 try {
	            service.getalldata();
	        	new Expectations() {{
	    			repo.findAll();
	    			result=available;
	    			
	    		}};
	            assertEquals(available, service.getalldata());
	        } 
		 catch (Exception e) {
	            assertEquals("No orders present in database", e.getMessage());
	        }
	}

	@Test
	public void updateorder() {
		List<SaveOrders> save=new ArrayList<>();
		service.updateorder(save);
		new Verifications() {{
			repo.findAll();
			
			
		}};
	}
	@Test
	public void changestatus() {
		
		Long num=(long) 123321;
		try {
			service.changestatus(num, "status", "date");
			new Verifications(){{
				repo.getById(num);
			
			}};
		}
		catch(Exception e){
			assertEquals("Invalid Tracking Number Entered", e.getMessage());
			
		}
	}
	@Test
	public void completedorders() {
		List<SaveOrders> orders=new ArrayList<>();
		SaveOrders s1=new SaveOrders();
		try {
			new Expectations() {{
				repo.findAll();
				result=orders;
				
			}};
			assertEquals(orders, service.completedorders("new@gmail.com"));
		}
		catch(Exception e) {
			assertEquals("You have no completed orders", e.getMessage());
			
		}
	}
}
