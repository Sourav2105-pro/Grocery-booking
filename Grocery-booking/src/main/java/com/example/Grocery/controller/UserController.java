package com.example.Grocery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Grocery.dto.OrderDTO;
import com.example.Grocery.entity.GroceryItem;
import com.example.Grocery.entity.Order;
import com.example.Grocery.service.GroceryItemService;
import com.example.Grocery.service.OrderService;

public class UserController {
    
	private final OrderService orderService;
	private final GroceryItemService groceryItemService;

    public UserController(OrderService orderService,GroceryItemService groceryItemService ) {
        this.orderService = orderService;
        this.groceryItemService = groceryItemService;
    }

    @GetMapping("/items")
    public List<GroceryItem> getAvailableItems() {
        return groceryItemService.getAllItems();
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
	
	
}
