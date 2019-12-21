package com.bridgelabz.fundoo.util;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
@Component
@Configuration
@PropertySource("classpath:info.properties")
public class JwtServiceProvider {
	@Value("${expTime}")
	private long expTime;
	@Value("${secret}")
	private String secret ;

	public String generateToken(Long userId) {
	return JWT.create().withClaim("userId", userId).sign(Algorithm.HMAC512(secret));
	}

	public Long parseToken(String token) {
	
	return JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("userId").asLong();
	}

}
