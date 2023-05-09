package com.test.resttemplate.Security;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {
	@Value("${jwt.secret-key}")
	private String SECRET_KEY;
	
	@Value("${jwt.expired-time}")
	private Long EXPIRATION_TIME;
	
	public boolean validate(String jwt) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature -> Message: {}", e);
			// TODO: handle exception
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token -> Message {}", e);
			// TODO: handle exception
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token -> Message {}", e);	
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token -> Message {}", e);
			// TODO: handle exception
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty -> Message {}", e);
		}
		return false;
	}
	public String getEmailFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public Claims getClaims(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return claims;
	}
	
	public Date getExpiration(String token) {
		return getClaims(token).getExpiration();
	}
	

	public boolean isExpired(String token) {
		try {
			Date expireDate = getExpiration(token);
			return expireDate.before(new Date());
		} catch (Exception e) {
			return true;
		}
	}
	
	public String generateJwtToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		final String autorities = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		return Jwts.builder().setId(Long.toString(userPrincipal.getId())).setSubject(userPrincipal.getMobileNumber())
				.claim("roles", autorities).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
	
}
