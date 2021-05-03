package com.assignment.ecommerce.eventstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.assignment.ecommerce.eventstore.entity.ProductPrice;
import com.assignment.ecommerce.eventstore.entity.UserActivity;
import com.assignment.ecommerce.eventstore.repository.ProductPriceRepository;
import com.assignment.ecommerce.eventstore.repository.UserActivityRepository;

@Service
public class EventService {
	@Autowired
	private ProductPriceRepository priceHistoryRepo;
	@Autowired
	private UserActivityRepository userActivitiesRepo;

	public Page<UserActivity> getUserActivities(int page, int size) {
		return userActivitiesRepo.findAll(PageRequest.of(page, size));
	}

	public Page<ProductPrice> getPrices(Long id, int page, int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, "date");
		return priceHistoryRepo.findByProductId(id, PageRequest.of(page, size, sort));
	}
}
