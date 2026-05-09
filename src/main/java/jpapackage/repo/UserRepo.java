package jpapackage.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapackage.entity.SignupTable;

public interface UserRepo extends JpaRepository<SignupTable,String> {
	
	Optional<SignupTable> findByEmail(String Email);
}
