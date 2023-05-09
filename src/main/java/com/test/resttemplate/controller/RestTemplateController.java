package com.test.resttemplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.resttemplate.request.PostRequest;
import com.test.resttemplate.service.RestTemplateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RestTemplateController {
	
	private final RestTemplateService restTemplateService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return restTemplateService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<Object> login(@RequestBody PostRequest request) {
		return restTemplateService.login(request);
	}
	
	@GetMapping("/department")
	public ResponseEntity<?> getAllDepartment() {
		return restTemplateService.getAllDepartment();
	}
}
