package jpapackage.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpapackage.dto.PricingRequestDto;
import jpapackage.dto.PricingResponseDto;
import jpapackage.entity.DeliveryPlace;
import jpapackage.repo.DeliveryPlaceRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PricingService {
	private static final int BASE_FEE = 35;

	// Keep surcharge keys aligned with the React dropdown values.
	private static final Map<String, Integer> PARCEL_TYPE_SURCHARGES = Map.of(
		"document", 0,
		"small-package", 20,
		"medium-package", 45,
		"large-package", 80,
		"fragile-item", 70
	);

	private static final Map<String, Integer> WEIGHT_SURCHARGES = Map.of(
		"up-to-500g", 0,
		"500g-to-2kg", 25,
		"2kg-to-5kg", 60,
		"5kg-to-10kg", 110
	);

	@Autowired
	private DeliveryPlaceRepo deliveryPlaceRepo;

	public PricingResponseDto calculateQuote(PricingRequestDto request) {
		String fromPlace = normalize(request.getFromPlace());
		String toPlace = normalize(request.getToPlace());
		String parcelType = normalize(request.getParcelType());
		String weightSlab = normalize(request.getWeightSlab());

		log.info(
			"Normalised pricing request: fromPlace='{}', toPlace='{}', parcelType='{}', weightSlab='{}'",
			fromPlace,
			toPlace,
			parcelType,
			weightSlab
		);

		if (fromPlace.isEmpty() || toPlace.isEmpty() || parcelType.isEmpty() || weightSlab.isEmpty()) {
			log.warn("Pricing quote validation failed because one or more required fields were empty.");
			throw new IllegalArgumentException("Pickup route, parcel type, and weight slab are required.");
		}

		DeliveryPlace route = deliveryPlaceRepo
			.findByFromLocationIgnoreCaseAndToLocationIgnoreCase(fromPlace, toPlace)
			.orElseThrow(() -> {
				log.warn("No delivery lane found for fromPlace='{}' and toPlace='{}'", fromPlace, toPlace);
				return new IllegalArgumentException("We could not find pricing for this delivery lane.");
			});

		log.info(
			"Delivery lane found: fromPlace='{}', toPlace='{}', distance={}, routePrice={}",
			route.getFromLocation(),
			route.getToLocation(),
			route.getDistance(),
			route.getPrice()
		);

		Integer parcelTypeCharge = PARCEL_TYPE_SURCHARGES.get(parcelType);
		if (parcelTypeCharge == null) {
			log.warn("Unsupported parcel type received: '{}'", parcelType);
			throw new IllegalArgumentException("Unsupported parcel type selected.");
		}

		Integer weightCharge = WEIGHT_SURCHARGES.get(weightSlab);
		if (weightCharge == null) {
			log.warn("Unsupported weight slab received: '{}'", weightSlab);
			throw new IllegalArgumentException("Unsupported weight slab selected.");
		}

		PricingResponseDto response = new PricingResponseDto();
		response.setFromPlace(route.getFromLocation());
		response.setToPlace(route.getToLocation());
		response.setParcelType(parcelType);
		response.setWeightSlab(weightSlab);
		response.setDistance(route.getDistance());
		response.setBaseFee(BASE_FEE);
		response.setDistanceCharge(route.getPrice());
		response.setParcelTypeCharge(parcelTypeCharge);
		response.setWeightCharge(weightCharge);
		response.setTotal(BASE_FEE + route.getPrice() + parcelTypeCharge + weightCharge);

		log.info(
			"Pricing breakdown: baseFee={}, routeCharge={}, parcelTypeCharge={}, weightCharge={}, total={}",
			response.getBaseFee(),
			response.getDistanceCharge(),
			response.getParcelTypeCharge(),
			response.getWeightCharge(),
			response.getTotal()
		);
		return response;
	}

	private String normalize(String value) {
		return value == null ? "" : value.trim().toLowerCase();
	}
}
