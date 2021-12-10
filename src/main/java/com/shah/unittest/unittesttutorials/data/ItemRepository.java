package com.shah.unittest.unittesttutorials.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shah.unittest.unittesttutorials.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
