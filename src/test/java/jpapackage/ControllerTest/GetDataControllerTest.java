package jpapackage.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jpapackage.controller.getdata;

import jpapackage.entity.SaveOrders;

import jpapackage.service.OrderDataService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class GetDataControllerTest {


    @Injectable
    private OrderDataService service;

    @Tested
    private getdata controller;
    private SaveOrders order;


    @Test
    public void getdatas() {

    	List<SaveOrders> save=new ArrayList<>();
    	
    	 new Expectations() {{
    		service.getdata("new@gmail.com");
    		result=save;
    		
    	}};
    	assertEquals(save,controller.getdatas("new@gmail.com"));
    
    }
    @Test
	public void saveorder() {
    	controller.saveorder(order);
		  new Verifications() {{
	            service.savedata(order);
	        }};
		

	}
    @Test
	public void saveorders() {
    	List<SaveOrders> save=new ArrayList<>();
    	controller.saveorders(save);
		  new Verifications() {{
	            service.updateorder(save);
	        }};
		

	}
    
    @Test
	public void  deletedata() {
		Long num=(long) 1233;
		controller.deletedata(num);
		  new Verifications() {{
	            service.removedata(num);
	        }};	

	}
	@Test
	public void getdata() {
		List<SaveOrders> allorders=new ArrayList<>();
    	
	   	 new Expectations() {{
	   		service.getalldata();
	   		result=allorders;
	   		
	   	}};
	   	assertEquals(allorders,controller.getdata());
	}

	@Test
	public void changestatus() {
		Long num=(long) 1233;
		controller.changestatus(num,"status","date");
		  new Verifications() {{
	            service.changestatus(num,"status","date");
	        }};	
		

	}
	@Test
	public void completedorders() {
		List<SaveOrders> completedorders=new ArrayList<>();
    	
	   	 new Expectations() {{
	   		service.completedorders("test@gmail.com");
	   		result=completedorders;
	   		
	   	}};
	   	assertEquals(completedorders,controller.completedorders("test@gmail.com"));
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
