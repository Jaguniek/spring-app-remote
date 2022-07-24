package com.jaguniek.products.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TreeNode {
	public TreeNode() {
		super();
	}
	String data;
    TreeNode left, right;
}
