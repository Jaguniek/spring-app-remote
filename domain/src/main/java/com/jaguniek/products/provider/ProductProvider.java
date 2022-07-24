package com.jaguniek.products.provider;

import com.jaguniek.products.model.Product;

public interface ProductProvider {

	Product fetch(String id);

}
