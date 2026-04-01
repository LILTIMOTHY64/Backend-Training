package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
		@Autowired
		private CustomerRepository repository;
		@GetMapping("/save")
		public String saveData() {
			Customer c = new Customer();
			c.setName("Neela");
			Order o1 = new Order();
			o1.setProduct("Laptop");
			o1.setPrice(50000);
			Order o2 = new Order();
			o2.setProduct("Mobile");
			o2.setPrice(20000);
			
			c.setOrders(Arrays.asList(o1,o2));
			repository.save(c);
			return "Data Saved!";
		}
		@GetMapping("/get")
		public Object getData() {
			return repository.findAll();
		}
}
