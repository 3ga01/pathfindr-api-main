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
                                                                                // "*",

                                                                                "http://path-findr.vercel.app/",
                                                                                "https://pathfindr-frontend-2ec0df532716.herokuapp.com",
                                                                                "/signUpStudent",
                                                                                "https://pathfindr-e70a2615f0f7.herokuapp.com",
                                                                                "https://pathfindr-e70a2615f0f7.herokuapp.com/signUpStudent",

                                                                                "http://localhost:8080/data",

                                                                                "http://localhost:3000/register",

                                                                                "http://localhost:3000/mentor",

                                                                                "/mentorApplication",
                                                                                "https://bgjm7rrm-8080.uks1.devtunnels.ms",
                                                                                "https://bgjm7rrm-8080.uks1.devtunnels.ms/signUpStudent",

                                                                                "http://localhost:3000",

                                                                                "http://localhost:3000/login",

                                                                                "https://pathfindr-e70a2615f0f7.herokuapp.com",
                                                                                "https://pathfindr-e70a2615f0f7.herokuapp.com/signUpStudent",
                                                                                "https://pathfindr-e70a2615f0f7.herokuapp.com/mentorApplication",

                                                                                "http://path-findr.vercel.app/register"));

                                                                // Specify allowed HTTP methods
                                                                config.setAllowedMethods(List.of(

                                                                                "GET",

                                                                                "POST",

                                                                                "PUT",

                                                                                "DELETE"));

                                                                // Specify allowed headers
                                                                config.setAllowedHeaders(List.of(

                                                                                "Content-Type", "Authorization"));

                                                                // Enable support for credentials (e.g.,cookies)
                                                                config.setAllowCredentials(true);
                                                                config.setMaxAge(3600L);
                                                                return config;
                                                        });
                                })

                                .authorizeHttpRequests(
                                                (request) -> request

                                                                .requestMatchers("/", "/signUpStudent",
                                                                                "/mentorApplication")
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

                                // .headers(
                                // (headers) -> headers

                                // .frameOptions(frameOptions -> frameOptions
                                // .sameOrigin()))

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
