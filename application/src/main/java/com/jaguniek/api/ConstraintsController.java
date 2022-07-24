package com.jaguniek.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaguniek.products.model.User;
import com.jaguniek.products.service.ConstraintService;

@RestController
@RequestMapping("/v1/constraints")
public class ConstraintsController {

	private ConstraintService constraintService;

	@GetMapping("/isAllowed")
	public ResponseEntity<Boolean> isAllowed(User user, String productId) {
		return ResponseEntity.ok(constraintService.isAllowed(user, productId));

	}
	// W zadaniu podano, że nie ma potrzeby implementacji kontrolera.
}
