package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signin.html", "/favicon.ico", "/conf.html").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/signin.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/conf.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/signin.html")
                        .permitAll()
                );
        return http.build();
    }
}
