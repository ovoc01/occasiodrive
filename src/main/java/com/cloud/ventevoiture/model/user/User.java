package com.cloud.ventevoiture.model.user;

import java.util.Collection;
import java.util.List;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cloud.ventevoiture.model.user.role.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_test")
public class User implements UserDetails{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   Integer id;
   String firstname;
   String lastName;
   String email;
   String password;

   @Enumerated(EnumType.STRING)
   Role role;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(role.name()));
   }
   @Override
   public String getUsername() {
     return this.email;
   }
   @Override
   public boolean isAccountNonExpired() {
      return true;
   }
   @Override
   public boolean isAccountNonLocked() {
     return true;
   }
   @Override
   public boolean isCredentialsNonExpired() {
     return true;
   }
   @Override
   public boolean isEnabled() {
      return true;
   }

}
