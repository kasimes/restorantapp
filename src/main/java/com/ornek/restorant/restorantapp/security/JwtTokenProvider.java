package com.ornek.restorant.restorantapp.security;

import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.repository.UsersRepository;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final UsersRepository usersRepository;
    //JWT olusturulurken kullanılacak gizli anahtar
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    //jwt gecerlilik suresi
    @Value("${app.jwt-expiration-ms}")
    private long jwtExpirationMs;

    public JwtTokenProvider(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //giriş yapan kullanıcıya token uretme

    public String generateToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();

        Users user = usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("kullanıcı bulunamadı"));


        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("role",user.getRole().name())
                .setIssuer(String.valueOf(now))
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // İmza algoritması ve gizli anahtar
                .compact();

    }

    //kullanıcı adı cıkarma
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)         // Gizli anahtarla token'ı çözümle
                .parseClaimsJws(token)            // Token'ı ayrıştır
                .getBody()
                .getSubject();

    }

    //token gecerlimi kontrol eder
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true; // Her şey yolundaysa token geçerli
        } catch (SignatureException ex) {
            System.out.println("Geçersiz JWT imzası");
        } catch (MalformedJwtException ex) {
            System.out.println("Geçersiz JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("JWT token süresi dolmuş");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Desteklenmeyen JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT token boş");
        }
        return false;
    }




}
