package com.example.ProjectFlights.utils;

import com.example.ProjectFlights.dto.UserDTO;
import com.example.ProjectFlights.dto.UserLoginDTO;
import com.example.ProjectFlights.dto.UserRegisterDTO;
import com.example.ProjectFlights.model.User;

public class UserDTOHelper {
    public static UserDTO userToUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setTelephone(user.getTelephone());
        return dto;
    }

    public static User registerDtoToUser(UserRegisterDTO dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());
        user.setPassword(dto.getPassword());
        return user;
    }
}
