package com.shah.unittest.unittesttutorials.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

/*
this is integration test
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest12 {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws JSONException {

		String response = this.restTemplate.getForObject("/all-items-from-database", String.class);
//		when strtict is false, u can omit any or all elements, as same number of (empty) objects is included
		JSONAssert.assertEquals("[{id:10001, name:Item1},{id:10002},{id:10003}]", response, false);
	}
}
