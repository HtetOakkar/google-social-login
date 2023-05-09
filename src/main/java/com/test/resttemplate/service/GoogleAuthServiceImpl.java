package com.test.resttemplate.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleAuthServiceImpl implements GoogleAuthService{
	
	private final GoogleIdTokenVerifier googleIdTokenVerifier;

	@Override
	public GoogleIdToken verifyGoogleIdToken(String token) {
		try {
			GoogleIdToken idToken = GoogleIdToken.parse(googleIdTokenVerifier.getJsonFactory(), token);
			if (googleIdTokenVerifier.verify(idToken)) {
				return idToken;
			}
		} catch (IOException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
