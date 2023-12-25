package com.java.basic.setup.global.config;

import com.java.basic.setup.domain.player.service.CustomUserDetailService;
import com.java.basic.setup.domain.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public SecurityConfig(CustomUserDetailService customUserDetailService){
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         * 5.6 이하버전 작성
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        **/

//        http
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .requestMatchers(
//                        "/swagger-ui/**",
//                        "/swagger-ui.html").permitAll()
//                .anyRequest().hasAnyRole("USER")
//                .and()
//                .httpBasic(httpBasic -> httpBasic.disable())//withDefaults()
//                .formLogin(
//                        (login) -> login.loginPage("/login")
//                )
//                .csrf(csrf -> csrf.disable())//Customizer.withDefaults()
//                .logout((logout) ->
//                        logout.logoutUrl("/logout")
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
