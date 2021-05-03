package com.assignment.ecommerce.eventstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.assignment.ecommerce.eventstore.entity.ProductPrice;

public interface ProductPriceRepository extends ElasticsearchRepository<ProductPrice, String> {

	Page<ProductPrice> findByProductId(Long id, Pageable pageable);
}
