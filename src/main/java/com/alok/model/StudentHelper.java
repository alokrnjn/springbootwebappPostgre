package com.alok.model;

public class StudentHelper {

	private String name;
	private String address;
	private int marks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public StudentHelper(String name, String address, int marks) {
		super();
		this.name = name;
		this.address = address;
		this.marks = marks;
	}
	public StudentHelper() {
		
	}
	 
}
