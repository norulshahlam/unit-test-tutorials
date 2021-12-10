package com.shah.unittest.unittesttutorials.business;
/*
basic demo of using unit test - testing the business layer
using diff arguments, expected result should differ as expected
*/
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessImplTest1 {

	@Test
	void calculateSum() {

		SomeBusinessImpl business = new SomeBusinessImpl();
		int actual = business.calculateSum(new int[] {1,2,3});
		int expected = 6;
		assertEquals(expected, actual);
	}

	@Test
	void calculateSumEmpty() {

		SomeBusinessImpl business = new SomeBusinessImpl();
		int actual = business.calculateSum(new int[] {});
		int expected = 0;
		assertEquals(expected, actual);
	}

}
