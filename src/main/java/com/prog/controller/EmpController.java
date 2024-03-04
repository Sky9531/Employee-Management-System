package com.prog.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prog.entity.Employee;
//import com.prog.service.EmpService;
import com.prog.repository.EmpRepo;

@RestController
//@Controller
public class EmpController {

	@Autowired
	private EmpRepo service;

	@GetMapping("/home")
	public String addEmp() {
		return "index";
	}

	@GetMapping("/show")
	private List<Employee>getEmployee(){
		return service.findAll();
	}
	
	@GetMapping("/getEmployee/{id}")   
	private Employee getEmployeeById(@PathVariable int id) {
		return service.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));
		
	}
	
	@RequestMapping("/addemp")
	private Employee addEmp(@RequestBody Employee emp) {
		return service.save(emp);
	}
	
	@PutMapping("/update/{id}")
	private ResponseEntity<Employee> Employeeupdate(@PathVariable int id, @RequestBody Employee Employee) {
		try {
			Employee resposEntity=getEmployeeById(id);
			return new ResponseEntity<Employee>(Employee,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Employee>(Employee,HttpStatus.NOT_FOUND);
				
		}
		
	}
	
	@RequestMapping("/delete/{id}")
	private String bookupdate(@PathVariable ("id") int id) {
		service.deleteById(id);
		return "Deleted successfully";
	}

	}