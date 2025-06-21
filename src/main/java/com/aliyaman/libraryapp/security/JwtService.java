package com.aliyaman.libraryapp.security;

import com.aliyaman.libraryapp.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    private final String SECRET_KEY = "iWKjCZOoys608C8hJ3zGB4vjxlYTSypMe8zDlQQ1YE12345";





    public String generateToken(UserDetails userDetails){

        Map<String , Object> claims = new HashMap<>();
        if (userDetails instanceof CustomUserDetails customUserDetails){
            List<String> roles = customUserDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            claims.put("roles" , roles);
        }
        return
        Jwts.builder()
                .setSubject(userDetails.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *2))
                .signWith(getKEy() , SignatureAlgorithm.HS256)
                .compact();
    }

   public Claims getClaims(String token){
        return 
        Jwts.parserBuilder()
                .setSigningKey(getKEy())
                .build()
                .parseClaimsJws(token)
                .getBody();
   }
   
   public <T> T exportToken(String token , Function<Claims , T> claimsTFunction){
        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);
   }
   
   public String getUserName(String token){
        return exportToken(token , Claims::getSubject);
   }
   
   public boolean isTokenExpired(String token){
        Date expiredDate = exportToken(token,Claims::getExpiration);
        return expiredDate.before(new Date());
   }
   
   public Object getClaims(String token , String key){
        Claims claims = getClaims(token);
        return claims.get(key);
   }

    public Key getKEy(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
