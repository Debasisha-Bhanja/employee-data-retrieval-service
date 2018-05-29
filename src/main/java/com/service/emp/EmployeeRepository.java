package com.service.emp;

import org.springframework.data.repository.Repository;

import com.service.emp.domain.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends Repository<Employee, String> {

	Flux<Employee> findAll();

	Mono<Employee> getByNameIgnoringCase(String name);
	
	Mono<Employee> getByIdIgnoringCase(String name);
	

}
