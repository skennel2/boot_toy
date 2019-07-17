package com.example.demo.example.rest;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * http://dummy.restapiexample.com/ 해당 사이트에서 Rest Api제공받음
 * 
 * @author skennel
 *
 */
@RestController
@RequestMapping(path = "/rest/api/")
public class RestTempateTest {

	@GetMapping(path = "/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws IOException {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("http://dummy.restapiexample.com/api/v1/")
				.build();

		// 해당 api의 accept header가 text/html타입이라 일단 String으로 받음
		String jsonData = restTemplate.getForObject("/employee/{id}", String.class, id);
		if (StringUtils.hasText(jsonData) && !jsonData.equals("false")) {
			Employee emp = new ObjectMapper()
					.readerFor(Employee.class)
					.readValue(jsonData);

			return ResponseEntity.ok(emp);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path = "/exchange/employee/{id}")
	public ResponseEntity<Employee> getEmployeeByIdUsingExchange(@PathVariable Long id) throws IOException {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("http://dummy.restapiexample.com/api/v1/")
				.build();
		
		ResponseEntity<String> response = restTemplate.exchange("/employee/{id}", HttpMethod.GET, null, String.class , id);
		
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			Employee emp = new ObjectMapper()
					.readerFor(Employee.class)
					.readValue(response.getBody());
			
			return ResponseEntity.ok(emp); 
		}
				
		return ResponseEntity.notFound().build();
	}

	@GetMapping(path = "/self/employee/{id}")
	public ResponseEntity<Employee> getEmployeeByIdUsingSelfCall(@PathVariable Long id) throws IOException {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("http://localhost:8080/rest/api/")
				.build();

		// 해당 api의 accept header가 application/json 바로 Serialize 가능함
		Employee data = restTemplate.getForObject("/employee/{id}", Employee.class, id);
		return ResponseEntity.ok(data);
	}
}
