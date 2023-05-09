package com.test.resttemplate.service;

import org.springframework.http.ResponseEntity;

import com.test.resttemplate.request.PostRequest;

public interface RestTemplateService {

	ResponseEntity<?> getAll();

	ResponseEntity<Object> login(PostRequest request);

	ResponseEntity<?> getAllDepartment();

}
