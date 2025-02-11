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

}
