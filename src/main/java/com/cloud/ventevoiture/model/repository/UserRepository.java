package com.cloud.ventevoiture.model.repository;

import java.util.Optional;

import com.cloud.ventevoiture.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
   Optional<User> findByEmail(String email);

   @Query(value = "select * from v_user_complete where email=:email",nativeQuery = true)
   Optional<User> findByEmailView(@Param("email") String email);

} 
