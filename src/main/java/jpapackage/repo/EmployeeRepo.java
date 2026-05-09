package jpapackage.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapackage.entity.EmployeeTable;

public interface EmployeeRepo extends JpaRepository<EmployeeTable,String>{
	Optional<EmployeeTable> findByEmail(String Email);

}
