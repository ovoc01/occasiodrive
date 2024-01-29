package com.cloud.ventevoiture.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloud.ventevoiture.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
   private final UserRepository userRepository;
   
   @Bean
   public UserDetailsService userDetailsService(){
      return new UserDetailsService() {
         @Override
         public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetails userDetails =  userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
            System.out.println(userDetails);
            return userDetails;
         }
      };
   }
   
   @Bean
   public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
      return config.getAuthenticationManager();
   }


}
