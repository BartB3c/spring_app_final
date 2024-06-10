package com.example.spring_app.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/login", "/register").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/product-list/**").permitAll()
//                                .requestMatchers("/book/add/**", "/book/update/**", "/delete").hasAuthority("ADMIN")
//                        .requestMatchers("/available_books").hasAnyAuthority("USER","ADMIN")
//                                .requestMatchers("/cart/**").hasAnyAuthority("USER","ADMIN")
                                .requestMatchers("/product-list").permitAll()
                        .anyRequest().authenticated()

//                ).csrf(csrf->csrf.ignoringRequestMatchers("/h2-console/**"))
                ).csrf(AbstractHttpConfigurer::disable)
                .headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .successHandler(new CustomLoginSuccessHandler())
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}