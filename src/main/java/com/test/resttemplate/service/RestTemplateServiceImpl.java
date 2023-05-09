package com.test.resttemplate.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.resttemplate.model.Department;
import com.test.resttemplate.model.JwtTokenResponse;
import com.test.resttemplate.request.PostRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestTemplateServiceImpl implements RestTemplateService {
	
	private final RestTemplate restTemplate;

	private final TokenStorage tokenStorage;
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	@Override
	public ResponseEntity<?> getAll() {
		String uri = "https://jsonplaceholder.typicode.com/posts";
		HttpEntity<String> entity = new HttpEntity<>(getHeaders());
		return restTemplate.getForEntity(uri, String.class, entity);
	}

	@Override
	public ResponseEntity<Object> login(PostRequest request) {
		String uri = "http://localhost:8080/api/auth/admin/login";
		HttpEntity<PostRequest> entity = new HttpEntity<>(request, getHeaders());
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
			String responseString = responseEntity.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			JwtTokenResponse response = new JwtTokenResponse();
			try {
				response = objectMapper.readValue(responseString, JwtTokenResponse.class);
				tokenStorage.storeToken(response.getToken());
				return ResponseEntity.ok(response);
			} catch (JsonProcessingException e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<>("error parsing json..", HttpStatus.BAD_REQUEST);
			}

		} catch (HttpClientErrorException e) {
			Map<String, Object> message = new HashMap<>();
			message.put("status", e.getRawStatusCode());
			message.put("message", e.getResponseBodyAsString());
			message.put("error", e.getStatusCode().getReasonPhrase());
			message.put("timestamp", Instant.now());
			return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
		}

	}

	@Cacheable("myCache")
	@Override
	public ResponseEntity<?> getAllDepartment() {
		String uri = "http://localhost:8080/api/department";
		String token = tokenStorage.retriveToken();
		HttpHeaders headers = getHeaders();
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<Department> department = new HttpEntity<>(headers);
		
		return restTemplate.exchange(uri, HttpMethod.GET,department, String.class);
	}
}
