

package com.example.joinair.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity

public class SpringSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/css/**"))
                .requestMatchers(new AntPathRequestMatcher("/js/**"))
                .requestMatchers(new AntPathRequestMatcher("/img/**"))
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico"));
    }
    /*@Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }*/
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
                        .failureUrl("/login-error")
                        .usernameParameter("User_Id")
                        .passwordParameter("User_Password")
//                        .successHandler(customAuthenticationSuccessHandler()) // 커스텀 성공 핸들러 설정
                        .defaultSuccessUrl("/index"))
//                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers(antMatcher("/admin/sales/report")).hasAuthority("admin")
                                .requestMatchers(antMatcher("/adminEditUserList")).hasAuthority("admin")
                                .requestMatchers(antMatcher("/productad/**")).hasAuthority("admin")
                                .requestMatchers(antMatcher("/dronead/**")).hasAuthority("admin")
                                .anyRequest().permitAll()
                )

                .headers(
                        headersConfigurer ->
                                headersConfigurer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                        )
                );
        /*http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(new MvcRequestMatcher(introspector, "/adminEditUserList")).hasAuthority("admin")
                                .requestMatchers(new MvcRequestMatcher(introspector, "/admin/sales/Report")).hasRole("admin")
                                .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())

                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true));*/




        return http.build();
    }


/*
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(new MvcRequestMatcher(introspector, "/**")).permitAll()
//                                .requestMatchers("/adminEditUserList").hasAuthority("admin")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }*/

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/adminEditUserList").hasAuthority("admin")
                        .anyRequest().permitAll())




                *//*.formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .failureUrl("/login?error")
                        .usernameParameter("user_id")
                        .passwordParameter("pw")
                        .defaultSuccessUrl("/index")
                )*//*
                *//*.logout(logout -> logout
                        .logoutUrl("/login/logout")
                        .logoutSuccessUrl("/index")
                        .invalidateHttpSession(true)
                );*//*
                .logout(withDefaults());
        return http.build();
    }*/
   /* @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests

                                //.requestMatchers("/", "/index","/about", "/membership", "productbut/list", "notice").permitAll()
                                //.requestMatchers("/adminWelcome","admin/sales/report", "/admin/sales/reportData", "/adminEditUserList").hasRole("admin")
                                .anyRequest().permitAll()
                )
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