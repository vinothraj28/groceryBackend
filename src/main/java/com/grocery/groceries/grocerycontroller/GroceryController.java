package com.grocery.groceries.grocerycontroller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.groceries.grocery.Grocery;

import com.grocery.groceries.grocery.service.GroceryService;

@RestController
@CrossOrigin(origins="http://localhost:3001")
public class GroceryController {
	
	@Autowired
	GroceryService groceryService;
	
	@GetMapping("/grocery")
	 public List<Grocery> getAllGrocery() { 
	        return (List<Grocery>) groceryService.getAllGrocery();
	    }
	
	 @PostMapping("/grocery")
	public String saveGrocery() {
		 groceryService.saveGrocery();
		 return "All Set";
	    }
	 
	 @GetMapping("/grocery/{name}")
	 public List<Grocery> getGrocery(@PathVariable("name") String name) {
	        return (List<Grocery>) groceryService.getGroceryByName(name);
	    }

}
