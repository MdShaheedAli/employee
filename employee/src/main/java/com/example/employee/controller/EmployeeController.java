package com.example.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.employee.dto.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@PostMapping
	public ResponseEntity<Map<String,Object>> saveEmployee(@RequestBody Employee emp) {
		return empService.saveEmp(emp);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
		return empService.getEmployee(id);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return empService.getAllEmployee();
	}
	
	
	@PutMapping
	public ResponseEntity<Map<String,Object>> updateEmp(@RequestBody Employee emp) {
		return empService.updateEmp(emp);
	}
	
	
	@DeleteMapping
	public ResponseEntity<Void> deleteEmp(Long id){
		return empService.deleteEmp(id);
	}
	
	
	@PostMapping("/saveFromFile")
	public ResponseEntity<String> saveFromFile(@RequestParam("file") MultipartFile file) {
		return empService.saveFromFile(file);
	}
}
