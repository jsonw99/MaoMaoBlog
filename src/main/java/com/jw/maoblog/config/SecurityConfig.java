package com.jw.maoblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * the security configuration
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // active security method settings
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String KEY = "jw.com";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   // use BCrypt to encode
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // define the encoding method
        return authenticationProvider;
    }

    /**
     * customize the config
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // access to everyone
                .antMatchers("/h2-console/**").permitAll() // access to everyone
                .antMatchers("/admins/**").hasRole("ADMIN") // access to specific user
                .and()
                .formLogin()   //the login based on form
                .loginPage("/login").failureUrl("/login-error") // customized login form
                .and().rememberMe().key(KEY) // activate remember me
                .and().exceptionHandling().accessDeniedPage("/403");  // handler the exception，if denny access then redirect to 403 page.
        http.csrf().ignoringAntMatchers("/h2-console/**"); // do not use CSRF on H2 console
        // by default, CSRF(Cross-Site Request Forgery) prevention is activate.
        http.headers().frameOptions().sameOrigin(); // allow the H2 queries from the same source
    }

    /**
     * Authentication Manager
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
}
