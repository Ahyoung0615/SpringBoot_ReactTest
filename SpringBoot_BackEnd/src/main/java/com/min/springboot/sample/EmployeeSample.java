package com.min.springboot.sample;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.min.springboot.model.Employee;
import com.min.springboot.repository.EmployeeRepository;

// TODO 005
@Configuration
public class EmployeeSample {

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
		// 한 번만 입력되게 샘플 값 넣어주고 주석처리
		return args -> {
//			Employee e1 = new Employee("kim", "bob", "kimbob@gmail.com");
//			Employee e2 = new Employee("lee", "yoon", "lee@gmail.com");
//			employeeRepository.saveAll(List.of(e1, e2));
		};
	}
}
