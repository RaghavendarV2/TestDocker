package com.rest_app.recipeApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean 
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
    http.csrf().disable()
        .authorizeRequests()
        .requestMatchers("/api/recipes/rights/**").hasRole("ADMIN") // Allow public access to customer endpoints
        .requestMatchers("/api/recipes/show/**").hasRole("USER")
                .anyRequest().authenticated()
        .and().httpBasic();
    
	return http.build();
	 
	}
	
	@Bean
	protected UserDetailsService userDetailsService(){
		UserDetails user1 = User.builder().username("Raghav")
				.password(passwordEncoder().encode("rag123")).roles("USER").build();
		
		UserDetails admin = User.builder().username("admin")
				.password(passwordEncoder().encode("adminrocks")).roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user1, admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
	
}
