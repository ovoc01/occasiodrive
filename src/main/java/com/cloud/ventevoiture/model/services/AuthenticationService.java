package com.cloud.ventevoiture.model.services;

import com.cloud.ventevoiture.controller.request.AuthenticationRequest;
import com.cloud.ventevoiture.controller.request.RegistrationRequest;
import com.cloud.ventevoiture.model.repository.UserRepository;
import com.cloud.ventevoiture.model.user.User;
import com.cloud.ventevoiture.model.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegistrationRequest register) throws Exception {
        if (userRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }
        var user = User.builder()
                .firstname(register.getFirstName())
                .lastName(register.getLastName())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

    }

    public String login(AuthenticationRequest login) throws UsernameNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        var user = userRepository.findByEmail(login.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return jwtService.generateToken(user);

    }
}
