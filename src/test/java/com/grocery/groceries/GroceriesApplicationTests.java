package com.grocery.groceries;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grocery.groceries.grocery.Grocery;
import com.grocery.groceries.grocerycontroller.GroceryController;



@SpringBootTest
class GroceriesApplicationTests {
	
	@Autowired
	GroceryController groceryController;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void getAllGroceryTest() {
		groceryController.saveGrocery();
		Assertions.assertFalse(groceryController.getAllGrocery().isEmpty());
	}
	
	@Test
	public void getGroceryTest() {
		Assertions.assertFalse(groceryController.getGrocery("Jumbu juice").isEmpty());
		
	}

}
