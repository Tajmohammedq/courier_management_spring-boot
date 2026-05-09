package jpapackage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpapackage.Exception.InvalidTrackingNumber;
import jpapackage.Exception.MissingFields;
import jpapackage.Exception.NoOrders;
import jpapackage.entity.SaveOrders;
import jpapackage.repo.ordertable;

@Service
public class OrderDataService {
	@Autowired
	private ordertable order;

	public List<SaveOrders> getdata(String email){
		List<SaveOrders> l=new ArrayList<>();
		try {
			Iterable<SaveOrders> orders=order.findAll();
			for(SaveOrders save:orders) {
				if(email.equals(save.getEmail()) && !save.getStatus().equals("Delivered")) {
					l.add(save);
				}
			}
		}
		catch(Exception e) {
			throw new NoOrders("No database found for orders");
		}

		if(l.isEmpty()) {
			throw new NoOrders("USER HAS NO ORDERS");
		}
		return l;
	}

	public OrderDataService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void savedata(SaveOrders saveorders) {
		if(saveorders.getEmail()==null||saveorders.getItem()==null) {
			throw new MissingFields("Mandatory fields are missing");
		}
		else {
			order.save(saveorders);
		}

	}

	public void removedata(Long number) {
		try {
			order.deleteById(number);
		}
		catch(Exception e){
			throw new NoOrders("No order present with this tracking number");

		}

	}

	public List<SaveOrders> getalldata() {
		List<SaveOrders> availableorders=new ArrayList<>();
		List<SaveOrders> orders=order.findAll();
		if(orders.size()<1) {
			throw new NoOrders("No orders present in database");
		}
		for(SaveOrders ord:orders) {
			if(ord.getOrder_status().equals("Not Taken")){
				availableorders.add(ord);
			}
		}
		if(availableorders.size()<1) {
			throw new NoOrders("No orders available for delivary");
		}
		return availableorders;
	}

	public void updateorder(List<SaveOrders> saveorders) {
		List<SaveOrders> allorders=order.findAll();
		for(SaveOrders coming:saveorders) {
			for(SaveOrders existing:allorders) {
				if(coming.getTrackingNumber().equals(existing.getTrackingNumber())){
					existing.setStatus(coming.getStatus());
					existing.setOrder_status(coming.getOrder_status());
					order.save(existing);
					break;
				}
			}
		}
	}

	public void changestatus(Long tracking,String status,String date) {

			SaveOrders orders=order.getById(tracking);
		try {
			orders.setStatus(status);
			orders.setDate(date);
			order.save(orders);
		}
		catch(Exception e) {
			throw new InvalidTrackingNumber("Invalid Tracking Number Entered");

		}




	}

	public List<SaveOrders> completedorders(String email) {
		List<SaveOrders> orders=new ArrayList<>();
		List<SaveOrders> completedorders=order.findAll();
		for(SaveOrders all:completedorders) {
			if(all.getEmail().equals(email) && all.getStatus().equals("Delivered")) {
				orders.add(all);

			}
		}
		if(orders.isEmpty()) {
			throw new NoOrders("You have no completed orders");
		}
		return orders;

	}

}
