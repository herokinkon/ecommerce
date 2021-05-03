package com.assignment.icommerce.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.icommerce.productservice.dto.ProductPriceDTO;
import com.assignment.icommerce.productservice.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService prodService;

	@PostMapping("/update-prices")
	public void updatePrice(@RequestBody List<ProductPriceDTO> productPrices) {
		this.prodService.updatePrices(productPrices);
	}

}
