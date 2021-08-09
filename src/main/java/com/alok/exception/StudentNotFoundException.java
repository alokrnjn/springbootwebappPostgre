package com.alok.exception;

public class StudentNotFoundException extends RuntimeException{

	public StudentNotFoundException() {
		
	}
	 public StudentNotFoundException(String e) {
		super(e);
	}
}
