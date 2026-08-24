package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.IF_REQUIRED
                        )
                )

                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")

                        .successHandler((request, response, authentication) -> {

                            String role = authentication.getAuthorities()
                                    .iterator()
                                    .next()
                                    .getAuthority();

                            if (role.equals("ROLE_APPLICANT")) {
                                response.sendRedirect("/vacancies");
                            } else {
                                response.sendRedirect("/resumes");
                            }
                        })

                        .failureUrl("/auth/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )


                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(
                                "/",
                                "/error",
                                "/auth/login",
                                "/auth/register",
                                "/auth/forgot_password",
                                "/auth/reset_password",
                                "/vacancies",
                                "/static/**",
                                "/images/**"
                        )
                        .permitAll()

                        .requestMatchers("/profile/**")
                        .authenticated()

                        .requestMatchers(
                                "/resumes/create",
                                "/resumes/*/edit"
                        )
                        .hasRole("APPLICANT")

                        .requestMatchers("/resumes")
                        .hasRole("EMPLOYER")

                        .requestMatchers(
                                "/vacancies/create",
                                "/vacancies/*/edit"
                        )
                        .hasRole("EMPLOYER")

                        .anyRequest()
                        .authenticated()
                );

        return http.build();
    }
}