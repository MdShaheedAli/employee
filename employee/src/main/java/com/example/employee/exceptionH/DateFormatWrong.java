package com.example.employee.exceptionH;

public class DateFormatWrong extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public DateFormatWrong(String msg) {
		super(msg);
		
	}

}
