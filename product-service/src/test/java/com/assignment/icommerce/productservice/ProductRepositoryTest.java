package com.assignment.icommerce.productservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.icommerce.productservice.entity.Product;
import com.assignment.icommerce.productservice.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository repo;

	@Test
	public void whenUpdateProductPrice_thenReturnProduct() {
		// given
		Product prod = repo.findById(1L).get();

		// when
		repo.updatePriceForId(new BigDecimal(400), 1L);
		entityManager.flush();

		BigDecimal price = repo.findById(1L).get().getPrice();
		// then
		assertThat(price).isEqualTo(prod.getPrice());
	}
}
