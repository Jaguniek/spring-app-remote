package com.jaguniek.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {

	private int productId;

	private String productName;

	private TreeNode constraints;
}
