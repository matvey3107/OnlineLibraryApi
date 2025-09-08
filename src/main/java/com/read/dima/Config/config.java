package com.read.dima.Config;

import com.read.dima.Service.CustomUserDetailService;
import com.read.dima.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class config {
    private final CustomUserDetailService customUserDetailService;
    private final JwtAuthenticationFilter jwtAuthenicationFilter;
    @Autowired
    public config(CustomUserDetailService customUserDetailService, JwtAuthenticationFilter jwtAuthenicationFilter) {
        this.customUserDetailService = customUserDetailService;
        this.jwtAuthenicationFilter = jwtAuthenicationFilter;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register/**", "/auth/login/**").permitAll()
                        .requestMatchers("/user/all").hasRole("ADMIN")
                        .requestMatchers("/book/add", "/book/update/**", "/book/delete/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/UserBook/update/**", "/UserBook/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).userDetailsService(customUserDetailService)
                .sessionManagement(session -> session .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenicationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailService)
                .passwordEncoder(encoder());
        return builder.build();
    }
}
