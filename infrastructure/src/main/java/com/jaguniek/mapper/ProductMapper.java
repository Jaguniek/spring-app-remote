package com.jaguniek.mapper;

import java.util.Stack;

import com.jaguniek.model.ProductDb;
import com.jaguniek.products.model.Product;
import com.jaguniek.products.model.TreeNode;

public class ProductMapper {

	// Customized mapper
	public Product map(ProductDb productDb) {
		if (productDb == null) {
			return null;
		}
		String constraints = productDb.getConstraints();
		return Product.builder().productId(productDb.getProductId()).productName(productDb.getProductName())
				.constraints(treeFromString(constraints,0,constraints.length()-1)).build();
	}

	// Method converting constrains String into TreeNode
	//TODO refactor
	private TreeNode treeFromString(String inputString, int beginIndex, int endIndex) {
		TreeNode root = null;

		//adding root node
		if (inputString.charAt(beginIndex) == '\'') {
			root = newNode(inputString.substring(beginIndex + 1, endIndex));
		} else {
			endIndex = inputString.indexOf('(');
			root = newNode(inputString.substring(beginIndex, endIndex));

			int subTreeBeginIndex = endIndex + 1;
			endIndex = findIndex(inputString, 0);

			// check if there is both right and left child of a node
			int commaIndex = inputString.indexOf(',');
			// logic if there are two children
			if (commaIndex > 0) {
				String subLeftTreeString = inputString.substring(subTreeBeginIndex, commaIndex - 1);
				if (inputString.substring(subTreeBeginIndex).charAt(0) != '\'') {
					commaIndex = findIndex(inputString, 1);
					subLeftTreeString = inputString.substring(subTreeBeginIndex, commaIndex);
				}
				String subRightTreeString = inputString.substring(commaIndex + 1, endIndex);
				root.setLeft(treeFromString(subLeftTreeString, 0, subLeftTreeString.length()));
				root.setRight(treeFromString(subRightTreeString, 0, subRightTreeString.length() - 1));
			// logic if there is only one child of a node
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

	/**
	 * The method is returning requested index based on inputString and type: 
	 * 		0-returns index of matching ')'
	 * 		1-returns index of "," separating two nodes children
	 * 
	 * @param inputString - String being subject to index search
	 * @param type - variable of type int determining what index will be found: 
	 * 		0-returns index of matching ')' 
	 * 		1-returns index of "," separating two nodes children
	 *  
	 * @return Index of chosen type
	 */
	private int findIndex(String inputString, int type) {
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
					if (type == 0) {
						// if stack is empty, this is
						// the required index
						if (s.isEmpty())
							return i;
					} else if (type == 1) {
						if (s.size() == 1)
							return i + 1;
					}
				}
			}
		}
		return -1;
	}
}