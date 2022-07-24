package com.jaguniek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jaguniek.products.provider.ProductProvider;
import com.jaguniek.products.service.ConstraintService;
import com.jaguniek.products.service.ConstraintServiceImpl;

@Configuration
public class BeanConfig {

	@Bean
	public ConstraintService constraintService(final ProductProvider productProvider) {
		return new ConstraintServiceImpl(productProvider);
	}
}
