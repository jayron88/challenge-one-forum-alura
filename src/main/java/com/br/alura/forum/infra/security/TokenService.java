package com.br.alura.forum.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.alura.forum.domain.usuario.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String tokenGenerator(Usuario usuario) {
		try {
		    var algoritmo  = Algorithm.HMAC256(secret);
		     return JWT.create()
		        .withIssuer("api")
		        .withSubject(usuario.getEmail())
		        .withExpiresAt(dataExpiracao())
		        .sign(algoritmo);
		} catch (JWTCreationException exception){
		   throw new RuntimeException("error ao gerar token jwt", exception);
		}
	}
	
	public String getSubject(String tokenJWT) {
		String token = tokenJWT;
		DecodedJWT decodedJWT;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		    		.withIssuer("api")
		    		.build()
		    		.verify(tokenJWT)
		    		.getSubject();
		        
		} catch (JWTVerificationException exception){
		    throw new RuntimeException("Token JWT inválido ou expirado");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
