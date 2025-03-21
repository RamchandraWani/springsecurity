package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
 	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
	
       
            http
            .authorizeHttpRequests((authorize) ->authorize.anyRequest().authenticated ()).
            httpBasic(Customizer.withDefaults());
        
            
            
            return http.build();
	} 
	@Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("Pavan")
                .password(passwordEncoder().encode( "pavan"))
                .roles("Normal")
                .build();
        UserDetails adminUser= User.builder()
                .username("aditya")
                .password(passwordEncoder().encode( "aditya"
                		))
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(normalUser, adminUser);
}
	
}



                   
            
