package com.assignment.icommerce.productservice.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.assignment.icommerce.productservice.entity.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Modifying
	@Query("update Product p set p.price = ?1 where p.id = ?2")
	int updatePriceForId(BigDecimal price, Long id);
}
