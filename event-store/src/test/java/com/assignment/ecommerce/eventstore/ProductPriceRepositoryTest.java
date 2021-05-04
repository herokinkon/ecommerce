package com.assignment.ecommerce.eventstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.ecommerce.eventstore.entity.ProductPrice;
import com.assignment.ecommerce.eventstore.repository.ProductPriceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventStoreApplication.class, properties = { "spring.cloud.discovery.enabled=false" })
public class ProductPriceRepositoryTest {

	@Autowired
	private ProductPriceRepository repo;

	@Test
	public void testGetProductPrice() {
		// given
		ProductPrice price = new ProductPrice();
		price.setProductId(1L);
		price.setPrice("123");

		List<ProductPrice> orgResults = repo.findByProductId(1L, PageRequest.of(0, 10)).getContent();

		// when
		ProductPrice saved = repo.save(price);

		Page<ProductPrice> priceHistories = repo.findByProductId(1L, PageRequest.of(0, 10));
		// then
		List<ProductPrice> results = priceHistories.getContent();
		assertThat(results.size() - orgResults.size()).isEqualTo(1);

		repo.delete(saved);
	}
}
