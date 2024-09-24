package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends  AbstractHttpConfigurer{

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(AbstractHttpConfigurer::disable) // this is for h2 to work

                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(List.of("http://localhost:4200"));
                            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(List.of("*"));
                            return config;
                        })
                )
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authz -> authz
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console
                        .anyRequest().permitAll() // Secure other endpoints

                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Manage sessions with cookies
                );


        return http.build();
    }
} // remove this bracket if u wanna use the bean below
/*
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Set the PasswordEncoder bean

        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return authenticationProvider.authenticate(authentication);
            }
        };
    }

} */ //this bean is about automatic login authetication i used to generate the sessions