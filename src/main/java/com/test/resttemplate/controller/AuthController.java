package com.test.resttemplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.test.resttemplate.request.GoogleAuthTokenRequest;
import com.test.resttemplate.service.GoogleAuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	private final GoogleAuthService googleAuthService;
	
	@PostMapping("/login/google")
	public ResponseEntity<?> getHello(@RequestBody GoogleAuthTokenRequest request) {
		GoogleIdToken idToken = googleAuthService.verifyGoogleIdToken(request.getId_token());
		if (idToken == null) {
			return ResponseEntity.badRequest().body("Invalid id token.");
		}
		
		String email = idToken.getPayload().getEmail();
		String name = (String)idToken.getPayload().get("name");
		String picture = (String) idToken.getPayload().get("picture");
		System.out.println(email);
		return ResponseEntity.ok(email + ", " + name + ", " + picture);
	}
}
