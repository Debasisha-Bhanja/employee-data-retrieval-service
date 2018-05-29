package com.service.emp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.emp.domain.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	private EmployeeRepository repository;

	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/employees")
	public Flux<Employee> all() {
		return this.repository.findAll(); 
	}

	/*@GetMapping(path = "/cities/{designation}")
	public Flux<Employee> all(String designation) {
		return this.repository.findAll().filter(this::isDesignationMatching);
	}*/
	
	@GetMapping(path = "/city/{name}")
	public Mono<Employee> byName(@PathVariable String name) {
		return this.repository.getByNameIgnoringCase(name);
	}

	private boolean isDesignationMatching(Employee emp,String designation) {
		return emp.getDesignation().equalsIgnoreCase(designation);
	}

}
