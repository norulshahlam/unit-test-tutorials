package com.shah.unittest.unittesttutorials.controller;

/*
unit test  - testing the business layer
test 1 controller,not all controllers.
this test for response returning JSON - 1 element, of type Item
also using matcher - expected status code, content

also, this test controllers that depend on other classes or interfaces. for this uri, the method uses 
beans from other class so we can use mock of this bean to resolve the dependency issue
 */
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.shah.unittest.unittesttutorials.business.ItemBusinessService;
import com.shah.unittest.unittesttutorials.model.Item;
	
@WebMvcTest(ItemController.class)
class ItemControllerTest8 {

	@MockBean
	private ItemBusinessService businessService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void itemsFromBusniessService() throws Exception {
		
		when(businessService.retrieveHardcodedItem())
		.thenReturn(new Item(2, "Item2", 10,10));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/item-from-business-service").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{id: 2,name:Item2, price:10, quantity:10}"))
				.andReturn();
	}

}
