package com.shah.unittest.unittesttutorials.controller;
/*
unit test  - testing the business layer
test 1 controller,not all controllers.
this test for response returning string
also using matcher - expected status code, content
*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
	


@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest5 {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void helloWorldBasic() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())	
				.andExpect(content().string("Hello World"))
				.andReturn();
		
//		above andExpect() can replace your below assertEquals() so u can remove
		assertEquals("Hello World", result.getResponse().getContentAsString());
	}

}
