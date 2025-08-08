package com.ornek.restorant.restorantapp.controller;


import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.model.enums.Role;
import com.ornek.restorant.restorantapp.model.request.LoginRequest;
import com.ornek.restorant.restorantapp.model.request.RegisterRequest;
import com.ornek.restorant.restorantapp.model.response.JwtAuthenticationResponse;
import com.ornek.restorant.restorantapp.repository.UsersRepository;
import com.ornek.restorant.restorantapp.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            //kullanıcı adı şifreyi nesne olustur

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String jwt = jwtTokenProvider.generateToken(authentication);
            return  ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }catch (AuthenticationException e) {
            // Giriş başarısızsa 401 dönebiliriz
            return ResponseEntity.status(401).body("Kullanıcı adı veya şifre hatalı");
        }


    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if(usersRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Kullanıcı zaten var");
        }

        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        if(registerRequest.getRole() !=null) {
            user.setRole(registerRequest.getRole());
        }
        else
        {
            user.setRole(Role.USER);//burda seçmezse eger defult user koyuyor
        }
        usersRepository.save(user);

        return ResponseEntity.ok("Kayıt Başarılı");
    }
}
