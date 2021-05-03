package com.assignment.icommerce.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assignment.icommerce.gateway.filter.ProductFilter;

@Configuration
public class SpringGatewayConfig {
	@Autowired
	private ProductFilter filter;

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("productService",
						r -> r.path("/products/**").filters(f -> f.filter(this.filter)).uri("lb://product-service"))
				.route("productService", r -> r.path("/audit/**").uri("lb://event-service")).build();
	}

}
