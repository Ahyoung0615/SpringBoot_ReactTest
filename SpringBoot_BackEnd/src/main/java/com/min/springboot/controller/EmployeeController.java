package com.min.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.min.springboot.exception.ResourceNotFoundException;
import com.min.springboot.model.Employee;
import com.min.springboot.repository.EmployeeRepository;

import io.micrometer.common.util.StringUtils;

// TODO 004
@RestController
@RequestMapping("api/v1")
// CROS Port 열어주기
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// 직원 전체 조회
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	// 직원 입력
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// 직원 Id로 상세 조회
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
											  .orElseThrow(() -> new ResourceNotFoundException(id + " 사원이 존재하지 않음"));
		return ResponseEntity.ok(employee);
	}
	
	// 수정(PATCH)
	@PatchMapping("/employees/{id}")
	@Transactional
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody  Employee inEmployee){
		Employee employee = employeeRepository.findById(id)
											  .orElseThrow(() -> new ResourceNotFoundException(id + " 사원이 존재하지 않음"));
		
		if(StringUtils.isNotBlank(inEmployee.getFirstName())) {
			employee.setFirstName(inEmployee.getFirstName());
		}
		if(StringUtils.isNotBlank(inEmployee.getLastName())) {
			employee.setLastName(inEmployee.getLastName());
		}
		if(StringUtils.isNotBlank(inEmployee.getEmailId())) {
			employee.setEmailId(inEmployee.getEmailId());
		}
		return ResponseEntity.ok(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
											  .orElseThrow(() -> new ResourceNotFoundException(id + " 사원이 존재하지 않음"));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
}
