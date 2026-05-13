package jpapackage.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapackage.entity.DeliveryPlace;

public interface DeliveryPlaceRepo extends JpaRepository<DeliveryPlace, Long> {
	Optional<DeliveryPlace> findByFromLocationIgnoreCaseAndToLocationIgnoreCase(String fromLocation, String toLocation);
}
