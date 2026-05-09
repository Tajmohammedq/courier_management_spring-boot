package jpapackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapackage.entity.TakenOrders;

public interface TakenOrdersRepo extends JpaRepository<TakenOrders, Long>{

}
