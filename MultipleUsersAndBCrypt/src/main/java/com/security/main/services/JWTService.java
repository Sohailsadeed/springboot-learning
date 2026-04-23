package com.security.main.services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	private static final String SECRET_KEY =
	        "my-very-secret-key-123456789012345678901234567890";
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("role", "USER");
		return Jwts.builder()
				.claims(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10))
				.signWith(getKey())
				.compact();
	}

	private Key getKey() { 
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); //the bytes are used to return a Key object
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims -> Claims.getSubject());
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
	    return Jwts.parser()
	            .verifyWith((SecretKey) getKey()) // Replaces setSigningKey
	            .build()              // Creates the parser
	            .parseSignedClaims(token) // Replaces parseClaimsJws
	            .getPayload();        // Replaces getBody
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims-> Claims.getExpiration());
	}	
}
