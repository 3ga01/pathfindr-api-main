package com.example.pathfindr.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                .cors(cors -> {
                                        cors
                                                        .configurationSource(request -> {

                                                                CorsConfiguration config = new CorsConfiguration();

                                                                config.setAllowedOrigins(List.of(

                                                                                "/signUpStudent",

                                                                                "http://localhost:8080/data",

                                                                                "http://localhost:3000/register",

                                                                                "http://localhost:3000/mentor",

                                                                                "/mentorApplication",

                                                                                "http://localhost:3000",

                                                                                "http://localhost:3000/login"));

                                                                // Specify allowed HTTP methods
                                                                config.setAllowedMethods(List.of(

                                                                                "GET",

                                                                                "POST",

                                                                                "PUT",

                                                                                "DELETE"));

                                                                // Specify allowed headers
                                                                config.setAllowedHeaders(List.of(

                                                                                "Content-Type"));

                                                                // Enable support for credentials (e.g.,cookies)
                                                                config.setAllowCredentials(true);
                                                                return config;
                                                        });
                                })

                                .authorizeHttpRequests(
                                                (request) -> request

                                                                .requestMatchers("/", "/signUp", "/mentorApplication")
                                                                .permitAll()

                                                                .requestMatchers("/student/**")
                                                                .hasAuthority("STUDENT")

                                                                .requestMatchers("/mentor/**").hasAuthority("MENTOR")
                                                                .anyRequest()
                                                                .permitAll())

                                .formLogin(
                                                (form) -> form

                                                                .loginPage("http://localhost:3000/login")

                                                                .defaultSuccessUrl("/signUp")

                                                                .failureUrl("/fail")

                                                                .usernameParameter("email")

                                                                .passwordParameter("password")
                                                                .permitAll()

                                                                .failureHandler((request, response, exception) -> {
                                                                        request.getSession().setAttribute("error",
                                                                                        "Invalid Credentials");

                                                                        response.sendRedirect("/login");
                                                                }))

                                .logout(
                                                (logout) -> logout

                                                                .invalidateHttpSession(true)

                                                                .logoutUrl("/logout")

                                                                .logoutSuccessUrl("/login"))

                                .headers(
                                                (headers) -> headers

                                                                .frameOptions(frameOptions -> frameOptions
                                                                                .sameOrigin()))

                                .csrf(AbstractHttpConfigurer::disable);

                return http.build();
        }

        // Inject Auth Manager
        @Bean
        public AuthenticationManager authenticationManager(

                        AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();

        }

        // inject password Encoder
        @Bean
        PasswordEncoder passwordEncoder() {

                return new BCryptPasswordEncoder();

        }

}
