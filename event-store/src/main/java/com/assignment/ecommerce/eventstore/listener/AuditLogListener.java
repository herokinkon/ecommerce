package com.assignment.ecommerce.eventstore.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.assignment.ecommerce.eventstore.entity.ProductPrice;
import com.assignment.ecommerce.eventstore.entity.UserActivity;
import com.assignment.ecommerce.eventstore.repository.ProductPriceRepository;
import com.assignment.ecommerce.eventstore.repository.UserActivityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuditLogListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogListener.class);
	@Autowired
	private UserActivityRepository userActivityRepo;
	@Autowired
	private ProductPriceRepository productPriceRepo;

	@JmsListener(destination = "auditRequest")
	public void auditUserActivity(String activity) {
		try {
			ObjectMapper obj = new ObjectMapper();
			UserActivity acti = obj.readValue(activity, UserActivity.class);
			userActivityRepo.save(acti);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@JmsListener(destination = "productPriceChange")
	public void auditProductPrice(String productPrice) {
		try {
			ObjectMapper obj = new ObjectMapper();
			ProductPrice price = obj.readValue(productPrice, ProductPrice.class);
			productPriceRepo.save(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
