package com.first_springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {



//    @Bean
//public UserDetailsManager userDetailsManager(DataSource source){
//
//    JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(source);
//
//    return userDetailsManager;
//}






    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests(auth -> auth
                  .requestMatchers("/players/**").hasAllRoles("ADMIN").anyRequest().authenticated()
                 // .requestMatchers(HttpMethod.GET,"/players/name").hasAllRoles("MANGAR").anyRequest().authenticated()
                )

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

//.anyRequest().authenticated()
    //.anyRequest().permitAll()




//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails userDetails1 = User.withUsername("mahmoud").password("{noop}123").roles("ADMIN","MANGAR","USER").build();
//        UserDetails userDetails2 = User.withUsername("ali").password("{noop}456").roles("MANGAR","USER").build();
//        UserDetails userDetails3 = User.withUsername("omar").password("{noop}789").roles("USER").build();
//
//
//
//
//
//        return new InMemoryUserDetailsManager(userDetails1,userDetails2,userDetails3);
//    }
//
//


@Bean
    public PasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder();
    }

}
