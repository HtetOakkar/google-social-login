package com.test.resttemplate.service;

import java.util.prefs.Preferences;

import org.springframework.stereotype.Component;

@Component
public class TokenStorageImpl implements TokenStorage {

	private final String PREF_NODE_NAME = "com.test.resttemplate";
	private final String TOKEN_KEY = "access_token";
	
	private Preferences getPreferences() {
		return Preferences.userRoot().node(PREF_NODE_NAME);
	}

	@Override
	public void storeToken(String token) {
		Preferences preferences = getPreferences();
		preferences.put(TOKEN_KEY, token);
	}

	@Override
	public String retriveToken() {
		Preferences preferences = getPreferences();
		return preferences.get(TOKEN_KEY, null);
	}

}
