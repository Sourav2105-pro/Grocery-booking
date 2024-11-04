package com.example.Grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Grocery.entity.GroceryItem;
import com.example.Grocery.repository.GroceryItemRepository;

@Service
public class GroceryItemService {

	private final GroceryItemRepository repository;
	
	public GroceryItemService(GroceryItemRepository repository) {
		this.repository = repository;
	}
	
	public GroceryItem addGroceryItem(GroceryItem item) {
		return repository.save(item);
	}
	
	public List<GroceryItem> getAllItems(){
		return repository.findAll();
	}
	
	public void deleteItem(Long id) {
		repository.deleteById(id);
	}
	
	public GroceryItem updateGroceryItem(Long id, GroceryItem item) {
		item.setId(id);
		return repository.save(item);
	}
	
	
}
