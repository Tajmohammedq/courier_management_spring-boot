package jpapackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapackage.entity.CancelledOrders;

public interface CancelledOrdersRepo extends JpaRepository<CancelledOrders, Long>{

}
