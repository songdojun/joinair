package com.example.joinair.config;

import com.example.joinair.entity.Role;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("user")
                .and()
                .withUser("admin").password("{noop}password").roles("admin");
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Order(0)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/admin/report")
                .securityMatcher("/admin/reportData")
                .securityMatcher("/adminEditUserList")
                .securityMatcher("/adminEditUser")
                .securityMatcher("/adminWelcome")
                .authorizeHttpRequests(request ->
                        request.anyRequest().hasRole("ADMIN"));

        return http.build();
    }
    @Bean
    @Order(1)
    public SecurityFilterChain systemFilterChain(HttpSecurity http) throws Exception{
        http
                .securityMatcher("/admin/**")
                .authorizeHttpRequests(request -> request
                        .anyRequest().hasAuthority("ROLE_ADMIN"));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(request -> request
//                        .anyRequest().authenticated()
                        .requestMatchers("/**").permitAll());
        return http.build();
    }
    /*@Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/productad/**").hasAuthority("ROLE_ADMIN")
//                                .requestMatchers("/productad").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/admin/sales/report").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/adminEditUserList").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/adminEditUser").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated())

                .logout(withDefaults());

        return http.build();
    }*/

    /*public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/status", "/images/**", "/membership", "/productbuy/list", "/about", "/index").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("User_Id")
                        .passwordParameter("User_Password")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                .logout(withDefaults());

        return http.build();
    }*/
}