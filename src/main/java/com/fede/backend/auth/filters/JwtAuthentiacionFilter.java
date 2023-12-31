package com.fede.backend.auth.filters;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fede.backend.auth.TokenJwtConfig;
import com.fede.backend.models.entities.Person;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.*;

public class JwtAuthentiacionFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthentiacionFilter( AuthenticationManager authenticationManager ) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response ) throws AuthenticationException {
        Person person = null;
        String password = null;
        String username = null;

        try {
            person = new ObjectMapper().readValue( request.getInputStream(), Person.class );
            username = person.getUsername();
            password = person.getPassword();
        } catch (StreamReadException e) {
            throw new RuntimeException( e );
        } catch (DatabindException e) {
            throw new RuntimeException( e );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( username, password );

        return authenticationManager.authenticate( token );
    }

    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult ) throws IOException, ServletException {
        String username =( (User) authResult.getPrincipal()).getUsername();

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        boolean isAdmin = roles.stream().anyMatch( rol -> rol.getAuthority().equals( "ROLE_ADMIN" ) );

        ClaimsBuilder claims = Jwts.claims();
        claims.add( "authorities",  new ObjectMapper().writeValueAsString( roles ));
        claims.add( "isAdmin", isAdmin  );
        claims.add( "username", username );

        String token = Jwts.builder()
                .claims(claims.build())
                .subject( username )
                .signWith( TokenJwtConfig.SECRET_KEY )
                .issuedAt( new Date() )
                .expiration( new Date(System.currentTimeMillis() + 3600000) )
                .compact();

        response.addHeader( TokenJwtConfig.AUTHORIZATION, TokenJwtConfig.BEARER + token);

        Map<String, Object> body = new HashMap<>();
        body.put( "token", token );
        body.put( "message", String.format( "Hola %s, has iniciado sesión con exito!", username ) );
        body.put( "username", username );

        response.getWriter().write( new ObjectMapper().writeValueAsString( body ) );
        response.setStatus( 200 );
        response.setContentType( "application/json" );
    }

    @Override
    protected void unsuccessfulAuthentication( HttpServletRequest request, HttpServletResponse response, AuthenticationException failed ) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();
        body.put( "message", "Usuario o contraseña incorrectos" );
        body.put( "error", failed.getMessage() );

        response.getWriter().write( new ObjectMapper().writeValueAsString( body ) );
        response.setStatus( 401 );
        response.setContentType( "application/json" );
    }
}
