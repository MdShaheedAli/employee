package com.example.employee.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.employee.dto.Employee;
import com.example.employee.exceptionH.DateFormatWrong;
import com.example.employee.repo.EmpRepository;

@Service
public class EmployeeService {

	@Autowired
	EmpRepository empRepo;

	public ResponseEntity<Map<String, Object>> saveEmp(Employee emp) {

		Employee em = empRepo.save(emp);
		Map<String, Object> resp = new HashMap<>();
		resp.put("employeeDetail", em);
		String resourceUrl = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(em.getId())
				.toUriString();
		resp.put("getUrl", resourceUrl);

		return ResponseEntity.status(201).body(resp);

	}

	public ResponseEntity<Employee> getEmployee(long id) {
		Employee em = empRepo.findById(id).orElse(null);
		if (em == null)
			return ResponseEntity.status(404).build();
		else
			return ResponseEntity.status(200).body(em);
	}

	public ResponseEntity<Map<String, Object>> updateEmp(Employee emp) {
		Employee employee = empRepo.findById(emp.getId()).orElse(null);
		if (employee == null)
			return ResponseEntity.status(404).build();
		else {
			Employee em = empRepo.save(emp);
			Map<String, Object> resp = new HashMap<>();
			resp.put("employeeDetail", em);
			String resourceUrl = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(em.getId()).toUriString();
			resp.put("getUrl", resourceUrl);

			return ResponseEntity.status(200).body(resp);
		}
	}

	public ResponseEntity<Void> deleteEmp(Long id) {
		Employee em = empRepo.findById(id).orElse(null);
		if (em != null) {
			empRepo.deleteById(id);
			return ResponseEntity.status(204).build();
		} else
			return ResponseEntity.status(404).build();
	}

	
	
	
	public ResponseEntity<String> saveFromFile(MultipartFile file) {
		File f = convertMultiPartToFile(file);
		List<Employee> employees = new ArrayList<>();

		try (FileReader reader = new FileReader(f);
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

			csvParser.forEach(csvRecord -> {
				Employee employee = new Employee();

				employee.setFirstName(csvRecord.get("First Name"));
				employee.setLastName(csvRecord.get("Second Name"));
				employee.setContactNumber(csvRecord.get("Contact Number"));
				employee.setDob(convertStringToDate1(csvRecord.get("Dob")));
				employee.setEmail(csvRecord.get("Email"));
				employee.setJoinDate(convertStringToDate(csvRecord.get("JoinDate")));
				employee.setRole(csvRecord.get("Role"));
				employee.setSalary(Double.parseDouble(csvRecord.get("Salary")));
				employee.setAddress(csvRecord.get("Address"));
				employee.setActive(Boolean.parseBoolean(csvRecord.get("Active")));

				employees.add(employee);
			});
			empRepo.saveAll(employees);
			
			

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return ResponseEntity.status(200).body("Success");
	}

	private Date convertStringToDate1(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return formatter.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DateFormatWrong(" Date format is wrong need like MM/dd/yyyy");
		}
	}

	private Date convertStringToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DateFormatWrong(" Date format is wrong need like yyyy-MM-dd");
			
		}
	}

	private File convertMultiPartToFile(MultipartFile file) {

//		File convFile = new File("C:\\Users\\admin\\Downloads\\extra "+file.getOriginalFilename());
		File convFile = new File(file.getOriginalFilename());

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convFile;
	}

	public ResponseEntity<List<Employee>> getAllEmployee() {
		
		Pageable p=PageRequest.of(0, 15,Sort.by("firstName").ascending().and(Sort.by("joinDate")));
		
		return  ResponseEntity.status(200).body(empRepo.findAll(p).getContent());
	}

}
