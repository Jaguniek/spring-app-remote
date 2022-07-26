package com.jaguniek.mapper;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jaguniek.model.ProductDb;
import com.jaguniek.products.model.Product;

public class ProductMapperTest {

	ProductMapper productMapper = new ProductMapper();

	@Test
	public void testOneLeaf() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50", "'VIP_ONLY'");
		Product product = productMapper.map(productdb);

		System.out.println("testOneLeaf ==========");
		System.out.println(product.getConstraints().getData());

		assertTrue(product.getConstraints().getData().equals("VIP_ONLY"));
	}

	@Test
	public void testNotOperator() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50", "NOT('VIP_ONLY')");
		Product product = productMapper.map(productdb);

		System.out.println("testNotOperator ==========");
		System.out.println(product.getConstraints().getData());
		System.out.println(product.getConstraints().getLeft().getData());

		assertTrue(product.getConstraints().getData().equals("NOT"));
		assertTrue(product.getConstraints().getLeft().getData().equals("VIP_ONLY"));
		assertNull(product.getConstraints().getRight());

	}

	@Test
	public void testOrOperatorWithTwoLeaves() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50", "OR('VIP_ONLY','CHICKEN_ONLY')");
		Product product = productMapper.map(productdb);

		System.out.println("testOrOperatorWithTwoLeaves ==========");
		System.out.println(product.getConstraints().getData());
		System.out.println(product.getConstraints().getLeft().getData());
		System.out.println(product.getConstraints().getRight().getData());

		assertTrue(product.getConstraints().getData().equals("OR"));
		assertTrue(product.getConstraints().getLeft().getData().equals("VIP_ONLY"));
		assertTrue(product.getConstraints().getRight().getData().equals("CHICKEN_ONLY"));

	}

	@Test
	public void testOrOperatorWithOneLeafAndOneNot() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50", "OR('VIP_ONLY',NOT('CHICKEN_ONLY'))");
		Product product = productMapper.map(productdb);

		System.out.println("testOrOperatorWithTwoLeaves ==========");
		System.out.println(product.getConstraints().getData());
		System.out.println(product.getConstraints().getLeft().getData());
		System.out.println(product.getConstraints().getRight().getData());
		System.out.println(product.getConstraints().getRight().getLeft().getData());

		assertTrue(product.getConstraints().getData().equals("OR"));
		assertTrue(product.getConstraints().getLeft().getData().equals("VIP_ONLY"));
		assertTrue(product.getConstraints().getRight().getData().equals("NOT"));
		assertTrue(product.getConstraints().getRight().getLeft().getData().equals("CHICKEN_ONLY"));

	}

	@Test
	public void testOrOperatorWithOneLeafAndOneOr() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50", "OR('VIP_ONLY',OR('CHICKEN_ONLY','PLAY_ONLY'))");
		Product product = productMapper.map(productdb);

		System.out.println("testOrOperatorWithTwoLeaves ==========");
		System.out.println(product.getConstraints().getData());
		System.out.println(product.getConstraints().getLeft().getData());
		System.out.println(product.getConstraints().getRight().getData());
		System.out.println(product.getConstraints().getRight().getLeft().getData());
		System.out.println(product.getConstraints().getRight().getRight().getData());

		assertTrue(product.getConstraints().getData().equals("OR"));
		assertTrue(product.getConstraints().getLeft().getData().equals("VIP_ONLY"));
		assertTrue(product.getConstraints().getRight().getData().equals("OR"));
		assertTrue(product.getConstraints().getRight().getLeft().getData().equals("CHICKEN_ONLY"));
		assertTrue(product.getConstraints().getRight().getRight().getData().equals("PLAY_ONLY"));

	}

	@Test
	public void testOrOperatorWithTwoNodesAndLeafes() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50",
				"OR('VIP_ONLY',OR(AND('CHICKEN_ONLY','ZZZ'),'PLAY_ONLY'))");
		Product product = productMapper.map(productdb);

		System.out.println("testOrOperatorWithTwoNodesAndLeafes ==========");
		// OR
		System.out.println(product.getConstraints().getData());
		// VIP_ONLY
		System.out.println(product.getConstraints().getLeft().getData());
		// OR
		System.out.println(product.getConstraints().getRight().getData());
		// AND
		System.out.println(product.getConstraints().getRight().getLeft().getData());
		// CHICKEN_ONLY
		System.out.println(product.getConstraints().getRight().getLeft().getLeft().getData());
		// ZZZ
		System.out.println(product.getConstraints().getRight().getLeft().getRight().getData());
		// PLAY_ONLY
		System.out.println(product.getConstraints().getRight().getRight().getData());

		assertTrue(product.getConstraints().getData().equals("OR"));

		assertTrue(product.getConstraints().getLeft().getData().equals("VIP_ONLY"));

		assertTrue(product.getConstraints().getRight().getData().equals("OR"));

		assertTrue(product.getConstraints().getRight().getLeft().getData().equals("AND"));
		assertTrue(product.getConstraints().getRight().getLeft().getLeft().getData().equals("CHICKEN_ONLY"));
		assertTrue(product.getConstraints().getRight().getLeft().getRight().getData().equals("ZZZ"));

		assertTrue(product.getConstraints().getRight().getRight().getData().equals("PLAY_ONLY"));
	}

	@Test
	public void testOrOperatorWithTwoNodesAndLeafesAndNot() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50",
				"OR(NOT('VIP_ONLY'),OR(AND('CHICKEN_ONLY','ZZZ'),'PLAY_ONLY'))");
		Product product = productMapper.map(productdb);

		System.out.println("testOrOperatorWithTwoNodesAndLeafesAndNot ==========");
		// OR
		System.out.println(product.getConstraints().getData());
		// NOT
		System.out.println(product.getConstraints().getLeft().getData());
		// VIP_ONLY
		System.out.println(product.getConstraints().getLeft().getLeft().getData());
		// OR
		System.out.println(product.getConstraints().getRight().getData());
		// AND
		System.out.println(product.getConstraints().getRight().getLeft().getData());
		// CHICKEN_ONLY
		System.out.println(product.getConstraints().getRight().getLeft().getLeft().getData());
		// ZZZ
		System.out.println(product.getConstraints().getRight().getLeft().getRight().getData());
		// PLAY_ONLY
		System.out.println(product.getConstraints().getRight().getRight().getData());

		assertTrue(product.getConstraints().getData().equals("OR"));

		assertTrue(product.getConstraints().getLeft().getData().equals("NOT"));
		assertTrue(product.getConstraints().getLeft().getLeft().getData().equals("VIP_ONLY"));

		assertTrue(product.getConstraints().getRight().getData().equals("OR"));

		assertTrue(product.getConstraints().getRight().getLeft().getData().equals("AND"));
		assertTrue(product.getConstraints().getRight().getLeft().getLeft().getData().equals("CHICKEN_ONLY"));
		assertTrue(product.getConstraints().getRight().getLeft().getRight().getData().equals("ZZZ"));

		assertTrue(product.getConstraints().getRight().getRight().getData().equals("PLAY_ONLY"));
	}

	@Test
	public void testDemoCase() {
		ProductDb productdb = new ProductDb(1234, "Lenovo P50",
				"OR('VIP_ONLY',AND(NOT('FOR_RICH_PEOPLE'),'WITH_RELATIONS'))");
		Product product = productMapper.map(productdb);

		System.out.println("testDemoCase ==========");
		// OR
		System.out.println(product.getConstraints().getData());
		// VIP_ONLY
		System.out.println(product.getConstraints().getLeft().getData());
		// AND
		System.out.println(product.getConstraints().getRight().getData());
		// NOT
		System.out.println(product.getConstraints().getRight().getLeft().getData());
		// FOR_RICH_PEOPLE
		System.out.println(product.getConstraints().getRight().getLeft().getLeft().getData());
		// WITH_RELATIONS
		System.out.println(product.getConstraints().getRight().getRight().getData());
		
		assertTrue(product.getConstraints().getData().equals("OR"));

		assertTrue(product.getConstraints().getLeft().getData().equals("VIP_ONLY"));

		assertTrue(product.getConstraints().getRight().getData().equals("AND"));

		assertTrue(product.getConstraints().getRight().getLeft().getData().equals("NOT"));
		assertTrue(product.getConstraints().getRight().getLeft().getLeft().getData().equals("FOR_RICH_PEOPLE"));
		
		assertTrue(product.getConstraints().getRight().getRight().getData().equals("WITH_RELATIONS"));
	}
}
