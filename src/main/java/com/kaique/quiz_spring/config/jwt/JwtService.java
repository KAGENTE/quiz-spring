package com.kaique.quiz_spring.config.jwt;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kaique.quiz_spring.model.User;
import com.kaique.quiz_spring.model.UserDTO;
import com.kaique.quiz_spring.repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Autowired
    private UserRepo repo;

    private String secretKey;
    
    public JwtService(){
        secretKey = generateSecretKey();
    }

    public String generateSecretKey(){
            try{
                KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
                SecretKey secretkey = keyGen.generateKey();
                System.out.println(secretKey);
                return Base64.getEncoder().encodeToString(secretkey.getEncoded());
            }catch (NoSuchAlgorithmException e){
                throw new RuntimeException("error generating key",e);
            }
        }


    public String generateToken(UserDTO user) {
        
        User u = repo.findByName(user.getName()).get();

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
        .setClaims(claims)
        .setSubject(u.getName())
        .claim("role",u .getRole())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
        .signWith(getKey(),SignatureAlgorithm.HS256).compact();
        
        
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
