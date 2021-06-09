package com.assignment.icommerce.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.assignment.icommerce.gateway.dto.UserActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class ProductFilter implements GatewayFilterFactory<ProductFilter.Config> {
	@Autowired
	private JmsTemplate template;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductFilter.class);

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return new GatewayFilter() {
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
		};
	}

	@Override
	public Class<Config> getConfigClass() {
		return ProductFilter.Config.class;
	}

	@Override
	public Config newConfig() {
		return new Config("ProductFilter");
	}

	public static class Config {
		public Config(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
