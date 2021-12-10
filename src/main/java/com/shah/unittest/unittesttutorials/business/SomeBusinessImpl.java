package com.shah.unittest.unittesttutorials.business;

import com.shah.unittest.unittesttutorials.data.SomeDataService;

public class SomeBusinessImpl {
	
	SomeDataService someDataService;
	
	public void setSomeDataService(SomeDataService someDataService) {
		this.someDataService = someDataService;
	}
	public int calculateSum(int[] data) {
		int sum=0;
		for(int value:data) {
			sum+=value;
		}
		return sum;
	}
	// u are retrieving data from interface
	public int calculateSumUsingDataService() {
		int sum=0;
		int[] data = someDataService.retrieveAllData();
		for(int value:data) {
			sum+=value;
		}
		return sum;
	}
}
