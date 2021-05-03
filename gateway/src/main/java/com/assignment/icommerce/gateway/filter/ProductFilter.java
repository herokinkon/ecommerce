package com.assignment.icommerce.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.assignment.icommerce.gateway.dto.UserActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class ProductFilter implements GatewayFilter {
	@Autowired
	private JmsTemplate template;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String url = exchange.getRequest().getPath().toString();
		String method = exchange.getRequest().getMethodValue();
		UserActivity activity = new UserActivity();
		activity.setMethod(method);
		activity.setUrl(url);
		try {
			ObjectMapper mapper = new ObjectMapper();
			String value = mapper.writeValueAsString(activity);
			template.convertAndSend("auditRequest", value);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return chain.filter(exchange);
	}

}
