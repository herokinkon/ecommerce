package com.assignment.icommerce.productservice;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.icommerce.productservice.controller.ProductController;
import com.assignment.icommerce.productservice.dto.ProductPriceDTO;
import com.assignment.icommerce.productservice.exception.NotFoundException;
import com.assignment.icommerce.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductAPITest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void whenValidInput_thenReturns200() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductPriceDTO> inputs = new ArrayList<>();
		ProductPriceDTO dto = new ProductPriceDTO();
		dto.setProductId(1L);
		dto.setPrice(new BigDecimal(123));
		inputs.add(dto);
		List<Long> emptyErrorIds = new ArrayList<>();
		Mockito.when(productService.updatePrices(ArgumentMatchers.anyList())).thenReturn(emptyErrorIds);

		mockMvc.perform(post("/products/update-prices").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(inputs))).andExpect(status().isOk());
	}

	@Test
	public void whenEmptyInput_thenReturns200() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductPriceDTO> inputs = new ArrayList<>();
		List<Long> emptyErrorIds = new ArrayList<>();
		Mockito.when(productService.updatePrices(ArgumentMatchers.anyList())).thenReturn(emptyErrorIds);

		mockMvc.perform(post("/products/update-prices").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(inputs))).andExpect(status().isOk());
	}

	@Test
	public void whenNotFoundInput_thenReturns500() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductPriceDTO> inputs = new ArrayList<>();
		List<Long> errorIds = new ArrayList<>();
		errorIds.add(1L);
		errorIds.add(3L);
		Mockito.when(productService.updatePrices(ArgumentMatchers.anyList())).thenReturn(errorIds);

		mockMvc.perform(post("/products/update-prices").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(inputs))).andExpect(status().is5xxServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}
}
