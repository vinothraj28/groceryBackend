package com.grocery.groceries.grocery.service;

import org.springframework.data.repository.CrudRepository;

import com.grocery.groceries.grocery.Grocery;

public interface GroceryRespository extends CrudRepository<Grocery, Integer> {} 
