package com.assignment.icommerce.productservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.icommerce.productservice.dto.ProductPriceDTO;
import com.assignment.icommerce.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository repo;
	@Autowired
	private JmsTemplate template;

	private static final String PROD_PRICE_CHANNEL = "productPriceChange";

	public List<Long> updatePrices(@NonNull List<ProductPriceDTO> products) {
		List<Long> notFoundIds = new ArrayList<>();
		products.forEach(prod -> {
			int updatedItems = repo.updatePriceForId(prod.getPrice(), prod.getProductId());
			if (updatedItems > 0) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					String value = mapper.writeValueAsString(prod);
					template.convertAndSend(PROD_PRICE_CHANNEL, value);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
			} else {
				notFoundIds.add(prod.getProductId());
			}
		});
		return notFoundIds;
	}

}
