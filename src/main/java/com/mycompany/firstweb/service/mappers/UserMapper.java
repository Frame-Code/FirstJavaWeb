package com.mycompany.firstweb.service.mappers;

import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.entities.User;

/**
 *
 * @author Artist-Code
 */
public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getName(), user.getLastNames(), user.getPhone());
    }
    
    public static User toEntity(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getLastName(), userDTO.getPhone());
    }

}
