package com.bdb.gestortareasws.web.config;

import com.bdb.gestortareasws.web.filters.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.bdb.gestortareasws.utilitarios.Constantes.*;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers(HttpMethod.GET, TASKS_API).permitAll();
                    auth.antMatchers(HttpMethod.POST, TASKS_API).permitAll();
                    auth.antMatchers(HttpMethod.PUT, TASKS_API).permitAll();
                    auth.antMatchers(HttpMethod.DELETE, TASKS_API).permitAll();
                    auth.anyRequest().permitAll();
                });

        return http.build();
    }
}
