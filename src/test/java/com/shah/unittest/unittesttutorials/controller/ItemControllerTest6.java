package com.shah.unittest.unittesttutorials.controller;

/*
unit test  - testing the business layer
test 1 controller,not all controllers.
this test for response returning JSON
also using matcher - expected status code, content
*/

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
	
@WebMvcTest(ItemController.class)
class ItemControllerTest6 {

	@MockBean
	private ItemBusinessService businessService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void dummyItemBasic() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andReturn();
	}

}
