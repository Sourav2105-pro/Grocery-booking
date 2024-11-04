package com.example.Grocery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Grocery.dto.GroceryItemDTO;
import com.example.Grocery.entity.GroceryItem;
import com.example.Grocery.service.GroceryItemService;

@RestController
@RequestMapping("/admin/items")
public class AdminController {

	
	private final GroceryItemService service;
	
	public AdminController(GroceryItemService service) {
		this.service = service;
	}
	
	 @PostMapping
	    public GroceryItem addItem(@RequestBody GroceryItemDTO itemDTO) {
	        return service.addGroceryItem(new GroceryItem(itemDTO));
	    }

	    @GetMapping
	    public List<GroceryItem> getAllItems() {
	        return service.getAllItems();
	    }

	    @DeleteMapping("/{id}")
	    public void deleteItem(@PathVariable Long id) {
	        service.deleteItem(id);
	    }

	    @PutMapping("/{id}")
	    public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItemDTO itemDTO) {
	        return service.updateGroceryItem(id, new GroceryItem(itemDTO));
	    }
	
	
}
