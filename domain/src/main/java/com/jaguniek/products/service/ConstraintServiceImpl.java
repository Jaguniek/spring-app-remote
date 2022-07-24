package com.jaguniek.products.service;

import com.jaguniek.products.model.Product;
import com.jaguniek.products.model.TreeNode;
import com.jaguniek.products.model.User;
import com.jaguniek.products.provider.ProductProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstraintServiceImpl implements ConstraintService {

	private final ProductProvider productProvider;

	@Override
	public Boolean isAllowed(User user, String productId) {
		Product product = productProvider.fetch(productId);
		TreeNode constraints = product.getConstraints();
		// TODO check if User matches constraints
		return true;
	}

}
