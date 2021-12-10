package com.shah.unittest.unittesttutorials.business;
/*
basic demo of using unit test - testing the business layer,
 for interface with different implementations using mokito
mocking is only done when a dependency requires an implementation or u want to 'mock' the instance without its actual behaviour
*/
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.shah.unittest.unittesttutorials.data.SomeDataService;


class SomeBusinessMockTest3 {
	

	SomeBusinessImpl business = new SomeBusinessImpl();
	SomeDataService dataServiceMock  = mock(SomeDataService.class);
	
	@BeforeEach
	public void before() {

		business.setSomeDataService(dataServiceMock);
	}

	@Test
	public void calculateSumUsingDataService_basic() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3});
		
		assertEquals(6, business.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
	
		assertEquals(0, business.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
	
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {5});
	
		assertEquals(5, business.calculateSumUsingDataService());
	}

}
