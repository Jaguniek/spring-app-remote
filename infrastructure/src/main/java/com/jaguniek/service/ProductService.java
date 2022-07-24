package com.jaguniek.service;

import com.jaguniek.mapper.ProductMapper;
import com.jaguniek.model.ProductDb;
import com.jaguniek.products.model.Product;
import com.jaguniek.products.provider.ProductProvider;

public class ProductService implements ProductProvider{

	ProductMapper productMapper = new ProductMapper();
	
	@Override
	public Product fetch(String id) {
		return productMapper.map(getById(id));
	}

	private ProductDb getById(String id) {
		//TODO implement call to ext service
		
		return new ProductDb(1234, "Lenovo P50",
				"OR('VIP_ONLY',AND(NOT('FOR_RICH_PEOPLE'),'WITH_RELATIONS'))");
	}

}
