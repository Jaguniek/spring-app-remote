package com.jaguniek.mapper;

import java.util.Stack;

import com.jaguniek.model.ProductDb;
import com.jaguniek.products.model.Product;
import com.jaguniek.products.model.TreeNode;

public class ProductMapper {

	public Product map(ProductDb productDb) {
		if (productDb == null) {
			return null;
		}

		return Product.builder().productId(productDb.getProductId()).productName(productDb.getProductName())
				.constraints(convertConstraints(productDb)).build();
	}

	private TreeNode convertConstraints(ProductDb productDb) {
		String constraints = productDb.getConstraints();
		return treeFromString(constraints, 0, constraints.length() - 1);
	}

	private TreeNode treeFromString(String inputString, int beginIndex, int endIndex) {
		TreeNode root = null;

		if (inputString.charAt(beginIndex) == '\'') {
			// int endIndex = inputString.indexOf('\'');
			root = newNode(inputString.substring(beginIndex + 1, endIndex));
		} else {
			endIndex = inputString.indexOf('(');
			root = newNode(inputString.substring(beginIndex, endIndex));

			int subTreeBeginIndex = endIndex + 1;
			endIndex = findMatchingBracketIndex(inputString);
			//endIndex = inputString.indexOf(')');
			int commaIndex = inputString.indexOf(',');
			if (commaIndex > 0) {
				String subLeftTreeString = inputString.substring(subTreeBeginIndex, commaIndex - 1);
				String subRightTreeString = inputString.substring(commaIndex + 1, endIndex);
				root.setLeft(treeFromString(subLeftTreeString, 0, subLeftTreeString.length()));
				root.setRight(treeFromString(subRightTreeString, 0, subRightTreeString.length() - 1));
			} else {
				String subLeftTreeString = inputString.substring(subTreeBeginIndex, endIndex);
				root.setLeft(treeFromString(subLeftTreeString, 0, subLeftTreeString.length() - 1));
			}
		}
		return root;
	}

	private TreeNode newNode(String data) {
		TreeNode node = new TreeNode(data, null, null);
		return (node);
	}

	private int findMatchingBracketIndex(String inputString) {
		Stack<Character> s = new Stack<>();
		for (int i = 0; i <= inputString.length() - 1; i++) {

			// if open parenthesis, push it
			if (inputString.charAt(i) == '(') {
				s.add(inputString.charAt(i));
			}
			// if close parenthesis
			else if (inputString.charAt(i) == ')') {
				if (s.peek() == '(') {
					s.pop();

					// if stack is empty, this is
					// the required index
					if (s.isEmpty())
						return i;
				}
			}
		}
		return -1;
	}
}