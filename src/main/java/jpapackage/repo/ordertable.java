package jpapackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapackage.entity.SaveOrders;

public interface ordertable extends JpaRepository<SaveOrders, Long>{



}