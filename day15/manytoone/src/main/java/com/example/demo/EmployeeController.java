package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	@GetMapping("/save")
	public String saveData() {
		Employee e1 = new Employee();
		e1.setName("Neela");
		Employee e2 = new Employee();
		e2.setName("Siva");
		return "Data Saved!";
	}
	@GetMapping("/get")
	public Object getData() {
		return repository.findAll();
	}
}
