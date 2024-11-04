package com.example.Grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Grocery.entity.GroceryItem;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem,Long> {

}
