package com.shah.unittest.unittesttutorials.business;
/*
basic demo of using unit test - testing the business layer,
 for interface with different implementations. notice how messy and long winded the code is
*/
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shah.unittest.unittesttutorials.data.SomeDataService;

class SomeDataServiceStub implements SomeDataService {

	@Override
	public int[] retrieveAllData() {
		return new int[] { 1, 2, 3 };
	}
}
	class SomeDataServiceEmptyStub implements SomeDataService {
		@Override
		public int[] retrieveAllData() {
			return new int[] {};
		}
	}

	class SomeDataServiceOneElementStub implements SomeDataService {
		@Override
		public int[] retrieveAllData() {
			return new int[] { 5 };
		}
	}



class SomeBusinessStubTest2 {

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceStub());
		int actualResult = business.calculateSumUsingDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceEmptyStub());
		int actualResult = business.calculateSumUsingDataService();//new int[] {}
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceOneElementStub());
		int actualResult = business.calculateSumUsingDataService();// new int[] { 5 }
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

}
