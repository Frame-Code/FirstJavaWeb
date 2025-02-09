package com.mycompany.firstweb.dto;

import java.io.Serializable;

/**
 *
 * @author Artist-Code
 */
public class UserDTO implements Serializable{
    private final String name;
    private final String lastName;
    private final String phone;

    public UserDTO(String name, String lastName, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "name=" + name + ", lastName=" + lastName + ", phone=" + phone + '}';
    }
}


