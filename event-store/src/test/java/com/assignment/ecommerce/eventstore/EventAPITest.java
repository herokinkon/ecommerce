package com.assignment.ecommerce.eventstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.ecommerce.eventstore.controller.EventController;
import com.assignment.ecommerce.eventstore.entity.ProductPrice;
import com.assignment.ecommerce.eventstore.entity.UserActivity;
import com.assignment.ecommerce.eventstore.service.EventService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EventController.class)
public class EventAPITest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EventService eventService;

	@Test
	public void whenUserActivityHasValidInput_thenReturns200() throws Exception {
		Page<UserActivity> result = new PageImpl<>(new ArrayList<>());
		Mockito.when(eventService.getUserActivities(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
				.thenReturn(result);

		mockMvc.perform(get("/audit/user-activities").param("size", "10").param("page", "0"))
				.andExpect(status().isOk());
	}

	@Test
	public void whenProductPriceValidInput_thenReturns200() throws Exception {
		Page<ProductPrice> result = new PageImpl<>(new ArrayList<>());
		Mockito.when(eventService.getPrices(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt())).thenReturn(result);

		mockMvc.perform(get("/audit/price-history").param("id", "1").param("size", "10").param("page", "0"))
				.andExpect(status().isOk());
	}

}
