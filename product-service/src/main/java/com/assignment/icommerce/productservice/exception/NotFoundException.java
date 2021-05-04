package com.assignment.icommerce.productservice.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotFoundException extends Exception {
	private static final long serialVersionUID = -1164932073415636687L;
	private List<Long> ids = new ArrayList<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundException.class);

	public NotFoundException(List<Long> ids) {
		this.ids = ids;
	}

	@Override
	public String getMessage() {
		ObjectMapper obj = new ObjectMapper();
		String message = "Product Ids not found: ";
		try {
			message = message + obj.writeValueAsString(this.ids);
		} catch (Exception e) {
			NotFoundException.LOGGER.error(e.getMessage());
		}
		return message;
	}
}
