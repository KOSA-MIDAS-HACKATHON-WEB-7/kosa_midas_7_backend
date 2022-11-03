package com.backend.kosa_midas_7_backend.security.config;

import com.backend.kosa_midas_7_backend.security.jwt.JwtFilter;
import com.backend.kosa_midas_7_backend.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and()
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()

                .authorizeRequests()
<<<<<<< HEAD
<<<<<<< HEAD
                .antMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
=======
=======
>>>>>>> c3d22bde4752d847ae92e3f8faa362db83776076
                .antMatchers("/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
>>>>>>> c3d22bde4752d847ae92e3f8faa362db83776076
                .anyRequest().authenticated()

                .and().addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
