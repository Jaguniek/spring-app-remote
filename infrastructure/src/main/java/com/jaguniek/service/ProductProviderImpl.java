package com.jaguniek.service;

import org.springframework.stereotype.Service;

import com.jaguniek.mapper.ProductMapper;
import com.jaguniek.model.ProductDb;
import com.jaguniek.products.model.Product;
import com.jaguniek.products.provider.ProductProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductProviderImpl implements ProductProvider{

	ProductMapper productMapper = new ProductMapper();
	
	@Override
	public Product fetch(String id) {
		ProductDb productDb = getById(id);
		return productMapper.map(productDb);
	}

	private ProductDb getById(String id) {
		//TODO implement call to ext service and exception handling
		
		return new ProductDb(1234, "Lenovo P50",
				"OR('VIP_ONLY',AND(NOT('FOR_RICH_PEOPLE'),'WITH_RELATIONS'))");
	}

}
