package com.cloud.ventevoiture.model.repository;

import java.util.Optional;

import com.cloud.ventevoiture.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer>{
   Optional<User> findByEmail(String email);

} 
