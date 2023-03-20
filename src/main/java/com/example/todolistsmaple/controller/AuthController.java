package com.example.todolistsmaple.controller;

import com.example.todolistsmaple.Dto.LoginRequest;
import com.example.todolistsmaple.Dto.LoginResponse;
import com.example.todolistsmaple.entity.User;
import com.example.todolistsmaple.repository.UserRepository;
import com.example.todolistsmaple.sercurity.custom.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auths")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticate;

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> signupUser(@RequestBody LoginRequest loginRequest ) {
        var userEntity = userRepository.findByUsername(loginRequest.getUsername());
        if (userEntity.isPresent()){
            return ResponseEntity.ok(" Account already exists");
        }
        var user = new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        userRepository.save(user);
        var token = jwtTokenUtil.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        var authentication = authenticate.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        var token = jwtTokenUtil.generateToken(loginRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
