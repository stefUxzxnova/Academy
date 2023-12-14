package com.example.ProjectFlights.service;

import com.example.ProjectFlights.dto.UserRegisterDTO;
import com.example.ProjectFlights.model.User;
import com.example.ProjectFlights.repository.UserRepository;
import com.example.ProjectFlights.utils.UserDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerUser(UserRegisterDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));//TODO hash the pass
        User user = UserDTOHelper.registerDtoToUser(dto);
        try {
            repository.save(user);
        }catch (Exception exception){
            throw exception;
        }
        return user;
    }
}
