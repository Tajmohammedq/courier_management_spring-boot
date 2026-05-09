package jpapackage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpapackage.Exception.InvalidTrackingNumber;
import jpapackage.Exception.NoTakenOrders;
import jpapackage.entity.TakenOrders;
import jpapackage.repo.TakenOrdersRepo;

@Service
public class TakenOrdersService {
	@Autowired
	TakenOrdersRepo repo;
	public void savedata(List<TakenOrders> orders) {

		for(TakenOrders order:orders) {
			repo.save(order);
		}

	}
	public List<TakenOrders> getdata(String email){
		List<TakenOrders> orders=new ArrayList<>();
		List<TakenOrders> allorders=repo.findAll();
		for(TakenOrders all:allorders) {
			if(all.getEmployee_email().equals(email) && !(all.getStatus().equals("Delivered"))) {
				orders.add(all);

			}
		}
		if(orders.isEmpty()) {
			throw new NoTakenOrders("Employee does not have any Completed orders");
		}

		return orders;
	}
	public void deleteorder(Long trackingnumber) {
			repo.deleteById(trackingnumber);



	}
	public void changestatus(Long tracking, String status,String date) {
		TakenOrders orders=repo.getById(tracking);
		try {
			orders.setStatus(status);
			orders.setDate(date);
			repo.save(orders);
		}
		catch(Exception e) {
			throw new InvalidTrackingNumber("Invalid Tracking number Entered");
		}
	}
	public List<TakenOrders> completedorders(String email) {
		List<TakenOrders> taken=new ArrayList<>();
		List<TakenOrders> allorders=repo.findAll();

		for(TakenOrders ord:allorders) {

			if(ord.getEmployee_email().equals(email) && ord.getStatus().equals("Delivered")) {
				System.out.println("coming in");
				taken.add(ord);
			}
		}
		if(taken.isEmpty()) {
			throw new NoTakenOrders("Employee doesnt have any completed orders");
		}
		return taken;
	}


}
