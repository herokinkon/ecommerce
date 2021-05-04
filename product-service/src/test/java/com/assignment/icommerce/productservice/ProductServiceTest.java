package com.assignment.icommerce.productservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.icommerce.productservice.dto.ProductPriceDTO;
import com.assignment.icommerce.productservice.repository.ProductRepository;
import com.assignment.icommerce.productservice.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@MockBean
	private ProductRepository repo;

	@MockBean
	private JmsTemplate template;

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public ProductService productService() {
			return new ProductService();
		}
	}

	@Autowired
	private ProductService productService;

	@Test
	public void whenValidInput_thenReturnsEmptyList() throws Exception {
		List<ProductPriceDTO> inputs = new ArrayList<>();
		ProductPriceDTO dto = new ProductPriceDTO();
		dto.setProductId(1L);
		dto.setPrice(new BigDecimal(123));
		inputs.add(dto);

		doNothing().when(template).convertAndSend(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
		Mockito.when(repo.updatePriceForId(ArgumentMatchers.any(BigDecimal.class), ArgumentMatchers.any(Long.class)))
				.thenReturn(1);

		List<Long> errorIds = productService.updatePrices(inputs);
		assertTrue(errorIds.isEmpty());
	}

	@Test
	public void whenNotFoundInput_thenReturnsErrorIds() throws Exception {
		List<ProductPriceDTO> inputs = new ArrayList<>();
		ProductPriceDTO dto = new ProductPriceDTO();
		dto.setProductId(1L);
		dto.setPrice(new BigDecimal(123));
		inputs.add(dto);

		doNothing().when(template).convertAndSend(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
		Mockito.when(repo.updatePriceForId(ArgumentMatchers.any(BigDecimal.class), ArgumentMatchers.any(Long.class)))
				.thenReturn(0);

		List<Long> errorIds = productService.updatePrices(inputs);
		assertThat(errorIds.size()).isEqualTo(1);
		assertThat(errorIds.get(0)).isEqualTo(1L);
	}
}
