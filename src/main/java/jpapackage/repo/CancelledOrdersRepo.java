package jpapackage.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapackage.entity.CancelledOrders;

public interface CancelledOrdersRepo extends JpaRepository<CancelledOrders, Long>{
	List<CancelledOrders> findByEmailIgnoreCase(String email);
}
