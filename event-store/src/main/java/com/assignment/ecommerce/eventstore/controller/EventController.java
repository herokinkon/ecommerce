package com.assignment.ecommerce.eventstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.ecommerce.eventstore.entity.ProductPrice;
import com.assignment.ecommerce.eventstore.entity.UserActivity;
import com.assignment.ecommerce.eventstore.service.EventService;

@RestController
@RequestMapping("/audit")
public class EventController {
	@Autowired
	private EventService service;

	@GetMapping("/user-activities")
	public Page<UserActivity> getUserActivities(@RequestParam("page") int page, @RequestParam("size") int size) {
		return service.getUserActivities(page, size);
	}

	@GetMapping("/price-history")
	public Page<ProductPrice> getPriceHistory(@RequestParam("id") long id, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		return service.getPrices(id, page, size);
	}
}
