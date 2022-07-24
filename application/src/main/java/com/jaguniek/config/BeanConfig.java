package com.jaguniek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.jaguniek.products.provider.ProductProvider;
import com.jaguniek.products.service.ConstraintService;
import com.jaguniek.products.service.ConstraintServiceImpl;

@Configuration
public class BeanConfig {

	@Bean
	public ConstraintService constraintService(final ProductProvider productProvider) {
		return new ConstraintServiceImpl(productProvider);
	}

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}
}
