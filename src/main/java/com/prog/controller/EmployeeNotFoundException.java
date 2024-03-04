package com.prog.controller;

public class EmployeeNotFoundException extends RuntimeException {
		 public EmployeeNotFoundException(int id) {
			 super("ID is not Available"+id);
		 }
}
