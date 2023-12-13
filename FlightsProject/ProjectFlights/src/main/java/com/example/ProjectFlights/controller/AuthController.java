package com.example.ProjectFlights.controller;

import com.example.ProjectFlights.dto.UserLoginDTO;
import com.example.ProjectFlights.dto.UserRegisterDTO;
import com.example.ProjectFlights.model.User;
import com.example.ProjectFlights.security.JwtUtil;
import com.example.ProjectFlights.service.AuthService;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto){
        User user = authService.registerUser(dto);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto){
        try {
            //if authentication is successful, an Authentication object is returned
            Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(dto.getEmail());
            Map<String, Object> response = new HashMap<>();
            response.put("jwt", jwt);
            response.put("user", authentication.getPrincipal());
            //var u = SecurityContextHolder.getContext().getAuthentication();
            return ResponseEntity.ok(response);
        }catch (AuthenticationException exception){
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }
}
