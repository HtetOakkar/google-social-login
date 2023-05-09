package com.test.resttemplate.service;

public interface TokenStorage {

	void storeToken(String token);
	
	String retriveToken();
}
