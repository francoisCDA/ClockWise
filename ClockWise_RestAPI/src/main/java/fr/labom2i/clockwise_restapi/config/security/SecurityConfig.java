package fr.labom2i.clockwise_restapi.config.security;

import fr.labom2i.clockwise_restapi.config.jwt.JwtAuthentificationEntryPoint;
import fr.labom2i.clockwise_restapi.config.jwt.JwtRequestFilter;
import fr.labom2i.clockwise_restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

public class SecurityConfig {


    private final UserService userService;
    private final JwtAuthentificationEntryPoint jwtAuthenticationEntryPoint;


    public SecurityConfig(UserService userService, JwtAuthentificationEntryPoint jwtAuthenticationEntryPoint) {
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
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/api/todo/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/api/todo/user").hasRole("USER")
//                        .anyRequest().authenticated()
                          .anyRequest().permitAll())

                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    // Injection de la configuration d'authentification.
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    // Bean pour obtenir le gestionnaire d'authentification.
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        // Obtention du gestionnaire d'authentification à partir de la configuration.
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
