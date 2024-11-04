package com.example.Grocery.dto;

import java.util.List;


public class OrderDTO {
	
	private Long userId;
	private List<OrderItemDTO> items;
	
	public List<OrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
