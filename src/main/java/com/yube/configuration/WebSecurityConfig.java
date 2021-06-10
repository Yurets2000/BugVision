package com.yube.configuration;

import com.yube.security.jwt.AuthEntryPointJwt;
import com.yube.security.jwt.AuthTokenFilter;
import com.yube.security.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final AuthTokenFilter authenticationJwtTokenFilter;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Метод налаштування обробки HTTP-запитів */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* Відключаємо налаштування безпеки щодо CORS та CSRF */
        http.cors().and().csrf().disable()
                /* Налаштовуємо відповідь модуля при спробі виконання запитів
                  з неаутентифікованим користувачем */
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                /* Відключаємо формування сеансів Spring Security. */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                /* Налаштовуємо набір URL-адресів, щодо яких не буде застосовуватись
                   перевірка наявності заголовків аутентифікації */
                .authorizeRequests().antMatchers("/api/**").permitAll()
                .antMatchers("/", "/csrf", "/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/configuration/ui", "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/configuration/security", "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated();
        /* Налаштовуємо фільтр, який буде виконувати аутентифікацію користувача
           за відповідними заголовками в запиті */
        http.addFilterBefore(authenticationJwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
    }
}