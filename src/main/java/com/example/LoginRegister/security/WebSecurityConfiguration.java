package com.example.LoginRegister.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    //UserDetails user:"user"  password:"42eb5dc4-50af-4a5c-81d7-f9fb1df8c979"
    //CustomUserDetails user:"nedim" password:"'$2a$10$RYeV.iZvfM/6Wm8TwchCVea92HK4Sf4MI/M0nGVp/xxFkRHFCblEG'"
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/")
                .hasAuthority("USER")
                .and()

                .authorizeRequests()
                .antMatchers("/register", "/saveUser")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()

                .logout()
                .permitAll()
                .and()

                .httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
