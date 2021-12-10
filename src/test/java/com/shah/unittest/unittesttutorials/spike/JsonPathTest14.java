package com.shah.unittest.unittesttutorials.spike;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathTest14 {

	@Test
	public void learning() {
		String responseFromService = "[" + "{\"id\":10000, \"name\":\"Pencil\", \"quantity\":5},"
				+ "{\"id\":10001, \"name\":\"Pen\", \"quantity\":15},"
				+ "{\"id\":10002, \"name\":\"Eraser\", \"quantity\":10}" + "]";

		DocumentContext context = JsonPath.parse(responseFromService);

		// GET LENGTH OF ARRAY
		int length = context.read("$.length()");
		System.out.println("LENGTH OF ARRAY: " + length);
		assertThat(length).isEqualTo(3);
		// GET ALL IDS
		List<Integer> ids = context.read("$..id");
		System.out.println("ALL IDS: " + ids);
		assertThat(ids).containsExactly(10000, 10001, 10002);

		// GET ELEEMNT AT INDEX 1
		System.out.println(context.read("$.[1]").toString());
		// GET 2 ELEMENT STARTING AT 0
		System.out.println(context.read("$.[0:]").toString());
		// GET ELEMENT WHERE ATTRIBUTE NAME=ERASER
		System.out.println(context.read("$.[?(@.name=='Eraser')]").toString());
		// GET ELEMENT WHERE ATTRIBUTE QUANTITY=5
		System.out.println(context.read("$.[?(@.quantity==5)]").toString());

	}
}
