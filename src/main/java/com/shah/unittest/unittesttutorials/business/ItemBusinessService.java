package com.shah.unittest.unittesttutorials.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shah.unittest.unittesttutorials.data.ItemRepository;
import com.shah.unittest.unittesttutorials.model.Item;

@Component
public class ItemBusinessService {
	
	@Autowired
	private ItemRepository repository;

	public Item retrieveHardcodedItem() {
		return new Item(1, "Ball",10,100);
	}
	
	public List<Item> retrieveAllItems(){
		List<Item> items = repository.findAll();
		//here we do additional logic - we times the price with quantity to set the value of value
		for(Item item:items) {
			item.setValue(item.getPrice() * item.getQuantity());
		}
		
		return items;
	}

}
