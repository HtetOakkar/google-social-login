package com.test.resttemplate.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenResponse {
	private String token;
	private String expiredAt;
	private Long checkin_id;
}
