package jpapackage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpapackage.entity.CancelledOrders;
import jpapackage.repo.CancelledOrdersRepo;

@Service
public class CancelledOrdersService {
	@Autowired
	private CancelledOrdersRepo repo;
	

	public void AddToCancelOrder(CancelledOrders data) {
		repo.save(data);
	}

	public List<CancelledOrders> getCancelledOrders(String email) {
		return repo.findByEmailIgnoreCase(email);
	}
}
