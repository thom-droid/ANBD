package com.ktx.ddep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktx.ddep.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    CustomUserDetailsService customUserDetailsService;

	//ignore static resources
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/resources/static/**");
    }

    // use customUserDetailService as authentication data
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    // configure 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // currently disabled for application run
                .authorizeRequests() // authorization per url
                .antMatchers("/", "/login","/loginerror", "/signup", "/join","/update/pw").permitAll() // anyone allowed
                .antMatchers("/secured/test", "/members/**").hasRole("USER") // only user allowed   
                .antMatchers("/market").hasRole("MARKETKEEPER") // only marketkeeper allowed
                .anyRequest().authenticated() // anyone else need to be authenticated
                .and() // login and logout configuration
                    .formLogin()
                    .loginPage("/login") // url for view
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/authenticate")
                    .failureForwardUrl("/loginerror?login_error=1")
                    .defaultSuccessUrl("/",true)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
    }

    // password encoder
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}