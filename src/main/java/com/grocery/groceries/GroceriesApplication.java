package com.grocery.groceries;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.grocery.groceries.grocery.service.GroceryService;


@SpringBootApplication
@RestController
public class GroceriesApplication {

	@Autowired
	static
	GroceryService groceryService;
	public static void main(String[] args) {
		SpringApplication.run(GroceriesApplication.class, args);
		
		
	}

}
