package com.shah.unittest.unittesttutorials.business;

/*
basic demo of using unit test - testing the business layer,
 for interface with different implementations using mokito using annotations,leading to shorter code
*/
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.shah.unittest.unittesttutorials.data.ItemRepository;
import com.shah.unittest.unittesttutorials.model.Item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ItemBusinessServiceTest10 {

	@Mock
	ItemRepository repository;

	@InjectMocks
	ItemBusinessService business;

	/*
	 * above code is same as:
	 * ItemBusinessService business = new ItemBusinessService(repository);
	 */

	@Test
	public void calculateSumUsingDataService_basic() {
		when(repository.findAll()).thenReturn(

				Arrays.asList(new Item(2, "Item2", 10, 10), new Item(3, "Item3", 20, 20)));

		List<Item> items = business.retrieveAllItems();
		assertEquals(100, items.get(0).getValue());
		assertEquals(400, items.get(1).getValue());
	}

}
