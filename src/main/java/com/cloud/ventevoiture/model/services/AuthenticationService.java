package com.cloud.ventevoiture.model.services;

import com.cloud.ventevoiture.controller.request.AuthenticationRequest;
import com.cloud.ventevoiture.controller.request.RegistrationRequest;
import com.cloud.ventevoiture.model.repository.PersonRepository;
import com.cloud.ventevoiture.model.repository.UserRepository;
import com.cloud.ventevoiture.model.user.Gender;
import com.cloud.ventevoiture.model.user.Person;
import com.cloud.ventevoiture.model.user.User;
import com.cloud.ventevoiture.model.user.role.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;

    @Transactional
    public void register(RegistrationRequest register) throws Exception {
        if (userRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }

        var user = User.builder()
                .role(Role.USER)
                .password(passwordEncoder.encode(register.getPassword()))
                .email(register.getEmail())
                .build();


        var person = Person.builder()
                .firstName(register.getFirstName())
                .gender((register.getGender()==0) ? Gender.FEMALE:Gender.MALE)
                .lastName(register.getLastName())
                .birthDate(Date.valueOf(register.getBirthDate()))
                .build();

        Person p = personRepository.save(person);
        user.setPerson(p);
        userRepository.save(user);
    }



    public String login(AuthenticationRequest login) throws UsernameNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        var user = userRepository.findByEmail(login.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return jwtService.generateToken(user);

    }
}
