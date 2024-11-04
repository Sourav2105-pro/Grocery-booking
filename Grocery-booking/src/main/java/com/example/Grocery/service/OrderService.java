package com.example.Grocery.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Grocery.dto.OrderDTO;
import com.example.Grocery.dto.OrderItemDTO;
import com.example.Grocery.entity.GroceryItem;
import com.example.Grocery.entity.Order;
import com.example.Grocery.entity.OrderItem;
import com.example.Grocery.repository.GroceryItemRepository;
import com.example.Grocery.repository.OrderRepository;

@Service
public class OrderService {
	
  private final OrderRepository orderRepository;
  private final GroceryItemRepository groceryItemRepository;
  
  public OrderService(OrderRepository orderRepository, GroceryItemRepository groceryItemRepository) {
	  this.orderRepository = orderRepository;
	  this.groceryItemRepository = groceryItemRepository;
  }
	
  @Transactional
  public Order createOrder(OrderDTO orderDTO) {
	  BigDecimal totalPrice = BigDecimal.ZERO;
	  List<OrderItem> orderItems = new ArrayList<>();
	  
	  for(OrderItemDTO itemDTO : orderDTO.getItems()) {
		  GroceryItem groceryItem = groceryItemRepository.findById(itemDTO.getItemId())
                  .orElseThrow(() -> new RuntimeException("Item not found with id " + itemDTO.getItemId()));

          // Check inventory
          if (groceryItem.getQuantity() < itemDTO.getQuantity()) {
              throw new RuntimeException("Insufficient quantity for item: " + groceryItem.getName());
          }

          // Update inventory
          groceryItem.setQuantity(groceryItem.getQuantity() - itemDTO.getQuantity());
          groceryItemRepository.save(groceryItem);

          // Calculate the total for this item and add to overall total
          BigDecimal itemTotal = groceryItem.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
          totalPrice = totalPrice.add(itemTotal);

          // Create OrderItem and add it to the list
          OrderItem orderItem = new OrderItem();
          orderItem.setItemId(groceryItem.getId());
          orderItem.setQuantity(itemDTO.getQuantity());
          orderItems.add(orderItem);
      }

      // Create the order
      Order order = new Order();
      order.setUserId(orderDTO.getUserId());
      order.setItems(orderItems);
      order.setTotalPrice(totalPrice);

      // Save the order
      return orderRepository.save(order);
  }
		  
	  
  }


