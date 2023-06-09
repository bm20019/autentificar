package com.mabmw.autentificar.config;

import com.mabmw.autentificar.filtro.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception{
        http.csrf().disable() // (2)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/example/publico/**").permitAll()
                        .requestMatchers("/example/get/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

/*    @Bean
    usuarioServiceImpl userDetailsService() {
        return new usuarioServiceImpl();
    }*/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
