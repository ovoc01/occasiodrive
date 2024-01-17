package com.cloud.ventevoiture.model.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
   private static final String SECRET_KEY= "1qAmtxQAGeIbs8WlvDQiWtxTfJnMqg05ZmkSytTba/NQSMq8eAG+XYQyQ29BkfC";

   public String extractUsername(String token){
      //Le subject du token est l'email de l'utilisateur
      return extractClaim(token, Claims::getSubject) ;
   }

   private Claims extractAllClaims(String token){
      return Jwts
            .parserBuilder()
            .setSigningKey(getSigninKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            ;
   }

   public String generateToken(UserDetails details){
      return generateToken(new HashMap<>(),details);
   }

   public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
      return Jwts
         .builder()
         .setClaims(extraClaims)
         .setSubject(userDetails.getUsername())
         .setIssuedAt(new java.util.Date(System.currentTimeMillis()))


         .setExpiration(new java.util.Date(System.currentTimeMillis()+1000*60*24))


         .signWith(getSigninKey(),SignatureAlgorithm.HS256)
         .compact();
   }

   public boolean isTokenValid(String token,UserDetails userDetails){
      final String userName = extractUsername(token);
      return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

   private boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   private Date extractExpiration(String token) {
      return extractClaim(token,Claims::getExpiration);
   }

   private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }



   private Key getSigninKey() {
      byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(keyBytes);
   }
   
}
