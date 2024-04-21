package fr.labom2i.config.security;


import fr.labom2i.config.jwt.JwtRequestFilter;
import fr.labom2i.config.jwt.JwtAuthenticationEntryPoint;
import fr.labom2i.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserRestService userService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    public SecurityConfig(UserRestService userService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userService = userService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/login").permitAll()
//                        .requestMatchers("/api/todo/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/api/todo/user").hasRole("USER")
                        .anyRequest().authenticated())
//                        .anyRequest().permitAll())

                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    // Injection de la configuration d'authentification.
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bean pour le filtre d'authentification JWT.
    @Bean
    public JwtRequestFilter jwtAuthenticationFilter() {
        // Création d'un nouveau filtre avec le service utilisateur.
        return new JwtRequestFilter(userService);
    }

    // Bean pour le chiffrement des mots de passe.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utilisation de BCrypt pour le chiffrement des mots de passe.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*")); // Permettre toutes les origines
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // Permettre toutes les méthodes
        configuration.setAllowedHeaders(Arrays.asList("*")); // Permettre tous les headers
        configuration.setAllowCredentials(true); // Important pour les cookies, l'autorisation, etc.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Appliquer cette configuration à tous les chemins
        return source;
    }


}

