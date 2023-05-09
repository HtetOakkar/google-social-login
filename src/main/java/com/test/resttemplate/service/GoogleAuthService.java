package com.test.resttemplate.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

public interface GoogleAuthService {
	
	GoogleIdToken verifyGoogleIdToken(String token);
}
