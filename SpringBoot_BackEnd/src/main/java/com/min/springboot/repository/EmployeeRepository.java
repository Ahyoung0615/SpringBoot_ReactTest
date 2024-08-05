package com.min.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.springboot.model.Employee;

// TODO 002
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
