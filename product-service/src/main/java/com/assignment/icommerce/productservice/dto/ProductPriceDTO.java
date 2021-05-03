package com.assignment.icommerce.productservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProductPriceDTO {

	private Long productId;
	private BigDecimal price;
	private Date date = new Date();

	public ProductPriceDTO() {

	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

}
