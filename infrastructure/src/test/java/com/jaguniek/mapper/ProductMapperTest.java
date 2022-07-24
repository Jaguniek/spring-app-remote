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
		ProductDb productdb= new ProductDb(1234,"Lenovo P50", "'VIP_ONLY'");
		Product product = productMapper.map(productdb);

		System.out.println("testOneLeaf ==========");
		System.out.println(product.getConstraints().getData());
		
		assertTrue(product.getConstraints().getData().equals("VIP_ONLY"));
	}
	
	@Test
	public void testNotOperator() {
		ProductDb productdb= new ProductDb(1234,"Lenovo P50", "NOT('VIP_ONLY')");
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
		ProductDb productdb= new ProductDb(1234,"Lenovo P50", "OR('VIP_ONLY','CHICKEN_ONLY')");
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
		ProductDb productdb= new ProductDb(1234,"Lenovo P50", "OR('VIP_ONLY',NOT('CHICKEN_ONLY'))");
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
	
//	@Test
//	public void testOrOperatorWithOneLeafAndOneOr() {
//		ProductDb productdb= new ProductDb(1234,"Lenovo P50", "OR('VIP_ONLY',OR('CHICKEN_ONLY','PLAY_ONLY'))");
//		Product product = productMapper.map(productdb);
//
//
//		System.out.println("testOrOperatorWithTwoLeaves ==========");
//		System.out.println(product.getConstraints().getData());
//		System.out.println(product.getConstraints().getLeft().getData());
//		System.out.println(product.getConstraints().getRight().getData());
//		System.out.println(product.getConstraints().getRight().getLeft().getData());
//		System.out.println(product.getConstraints().getRight().getRight().getData());
//		
//		assertTrue(product.getConstraints().getData().equals("OR"));
//		assertTrue(product.getConstraints().getLeft().getData().equals("VIP_ONLY"));
//		assertTrue(product.getConstraints().getRight().getData().equals("OR"));
//		assertTrue(product.getConstraints().getRight().getLeft().getData().equals("CHICKEN_ONLY"));
//		assertTrue(product.getConstraints().getRight().getRight().getData().equals("PLAY_ONLY"));
//			
//	}
	
}
