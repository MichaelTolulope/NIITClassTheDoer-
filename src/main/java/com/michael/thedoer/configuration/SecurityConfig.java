package com.michael.thedoer.configuration;

import com.michael.thedoer.filters.JwtAuthFilter;
import com.michael.thedoer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    protected AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }


    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        "/thedoer/v1/auth/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}