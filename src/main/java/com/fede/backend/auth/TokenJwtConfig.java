package com.fede.backend.auth;

import io.jsonwebtoken.Jwts;

import java.security.Key;

public class TokenJwtConfig {
    public final static Key SECRET_KEY = Jwts.SIG.HS256.key().build();
    public final static String BEARER = "Bearer ";
    public final static String AUTHORIZATION = "Authorization";
}
