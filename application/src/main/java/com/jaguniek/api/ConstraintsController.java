package com.jaguniek.api;

import java.util.concurrent.Callable;

import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaguniek.products.model.User;
import com.jaguniek.products.service.ConstraintService;

@RestController
@RequestMapping("/v1/constraints")
public class ConstraintsController {

	private ConstraintService constraintService;

	// timeout based on https://www.baeldung.com/spring-rest-timeout
	@Retryable(maxAttemptsExpression = "#{${max.retry.attempts:10}}")
	@GetMapping("/isAllowed")
	public Callable<Boolean> isAllowed(@RequestParam User user, @RequestParam String productId) {
		return () -> {
			return constraintService.isAllowed(user, productId);
		};
	}
}
