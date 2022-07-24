package com.jaguniek.products.service;

import com.jaguniek.products.model.User;

public interface ConstraintService {
	Boolean isAllowed (User user, String productId);
}
