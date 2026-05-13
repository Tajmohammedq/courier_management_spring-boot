package jpapackage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jpapackage.dto.PricingRequestDto;
import jpapackage.dto.PricingResponseDto;
import jpapackage.service.PricingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3333")
public class PricingController {
	@Autowired
	private PricingService pricingService;

	@PostMapping(
		path = "/user/pricing/quote",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> calculateQuote(@RequestBody PricingRequestDto request) {
		log.info(
			"Received pricing quote request: fromPlace='{}', toPlace='{}', parcelType='{}', weightSlab='{}'",
			request.getFromPlace(),
			request.getToPlace(),
			request.getParcelType(),
			request.getWeightSlab()
		);

		try {
			PricingResponseDto quote = pricingService.calculateQuote(request);
			log.info(
				"Pricing quote calculated successfully: fromPlace='{}', toPlace='{}', total={}",
				quote.getFromPlace(),
				quote.getToPlace(),
				quote.getTotal()
			);
			return ResponseEntity.ok(quote);
		} catch (IllegalArgumentException exception) {
			log.warn("Pricing quote request rejected: {}", exception.getMessage());
			return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
		}
	}
}
