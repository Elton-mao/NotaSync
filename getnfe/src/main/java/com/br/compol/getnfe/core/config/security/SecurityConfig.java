package com.br.compol.getnfe.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/clientes/cadastrar") // opcional se estiver fazendo POST sem token CSRF
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/gerardanfe").permitAll() 
            .anyRequest().permitAll()
        )
        .formLogin(form -> form
            .loginPage("/auth/login")
            .defaultSuccessUrl("/dashboard", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/logout/?logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        )
        .build();
}

        @Bean
        WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring()
                    .requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**", "/img/**", "/vendor/**", "/favicon.ico");
        }

}
 