package com.example.employee.exceptionH;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerForEmp {
	
	@ExceptionHandler(DateFormatWrong.class)
	public ResponseEntity<Map<String,String>> handleDateFormat(DateFormatWrong d){
		
		Map<String,String> m= new HashMap<>();
		m.put("time", new Date().toString());
		m.put("errorMsg", d.getMessage());
		m.put("status", "500");
		return ResponseEntity.status(500).body(m);
		
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handleDateFormat(Exception d){
		
		Map<String,String> m= new HashMap<>();
		m.put("time", new Date().toString());
		m.put("errorMsg", d.getMessage());
		m.put("status", "500");
		return ResponseEntity.status(500).body(m);
		
		
	}

}
