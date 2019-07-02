package com.example.demo.rest;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/rest/api/")
public class RestTempateTest {

	@GetMapping(path = "/employee/{id}")
	public ResponseEntity<Employee> getAllEmployees(@PathVariable Long id) throws IOException{
		RestTemplate restTemplate = new RestTemplateBuilder()
				.defaultMessageConverters()
				.rootUri("http://dummy.restapiexample.com/api/v1/")				
				.build();
		
		String data = restTemplate.getForObject("/employee/{id}", String.class, id);
		
		Employee emp =  new ObjectMapper()
				.readerFor(Employee.class)
				.readValue(data);
		
		return ResponseEntity.ok(emp);
	}
}
