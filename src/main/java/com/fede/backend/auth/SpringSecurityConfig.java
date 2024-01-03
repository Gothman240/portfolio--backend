package com.fede.backend.auth;

import com.fede.backend.auth.filters.JwtAuthentiacionFilter;
import com.fede.backend.auth.filters.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain( HttpSecurity httpSecurity ) throws Exception {
        httpSecurity.authorizeHttpRequests( req -> req.requestMatchers( HttpMethod.POST, "/users" ).permitAll()
                        .requestMatchers( HttpMethod.GET, "/users" ).permitAll()
                        .requestMatchers( HttpMethod.GET, "/profile/{username}" ).permitAll()
                        .requestMatchers( HttpMethod.POST, "/profile" ).hasAnyAuthority( "ROLE_USER", "ROLE_ADMIN" )
                        .anyRequest().permitAll()
                ).addFilter( new JwtAuthentiacionFilter( authenticationConfiguration.getAuthenticationManager() ) )
                .addFilter( new JwtValidationFilter( authenticationConfiguration.getAuthenticationManager() ) )
                .csrf( AbstractHttpConfigurer::disable )
                .sessionManagement( httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .cors( corsConfigurer -> corsConfigurer.configurationSource( corsConfigurationSource() ) );

        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins( Arrays.asList( "http://localhost:8080", "http://localhost:8080/h2-console/**") );
        configuration.setAllowedMethods( Arrays.asList( "GET", "POST", "PUT", "DELETE", "OPTIONS" ) );
        configuration.setAllowedHeaders( Arrays.asList( "Authorization", "Content-Type", "X-Requested-With" ) );
        configuration.setAllowCredentials( true );

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", configuration );

        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter( corsConfigurationSource() )
        );
        bean.setOrder( Ordered.HIGHEST_PRECEDENCE );

        return bean;
    }
}
