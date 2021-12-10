package com.shah.unittest.unittesttutorials.controller;

/*
unit test  - testing the data layer
test 1 controller,not all controllers.
this test for response returning JSON ARRAY
also using matcher - expected status code, content

also, this test controllers that depend on other classes or interfaces. for this uri, the method uses 
beans from other class so we can use mock of this bean to resolve the dependency issue
 */
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.shah.unittest.unittesttutorials.business.ItemBusinessService;
import com.shah.unittest.unittesttutorials.model.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
	
@WebMvcTest(ItemController.class)
class ItemControllerTest9 {

	@MockBean
	private ItemBusinessService businessService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void retrieveAllItems () throws Exception {
		
		when(businessService.retrieveAllItems())
		.thenReturn(
				Arrays.asList(
						new Item(2, "Item2", 10,10),
						new Item(3, "Item3", 20,20)
						));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/all-items-from-database").accept(MediaType.APPLICATION_JSON);
		// make sure the content is wraaped in square brackets for json array!
		// 
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{id: 2,name:Item2, price:10, quantity:10},"
						+ "{id: 3,name:Item3, price:20, quantity:20}]"))
				
/*	
 make sure no of elements in mock is same in matchers. if strict is false, u can use empty object 
 * 			.andExpect(content().json("[{id: 2,name:Item2, price:10, quantity:10},"
+ "{}]"))
*/
				.andReturn();
	}
}