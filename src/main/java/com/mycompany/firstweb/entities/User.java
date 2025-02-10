
package com.mycompany.firstweb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Artist-Code
 */

@Entity(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    private String name;
    private String lastNames;
    private String phone;

    public User() {
    }

    public User(Long idUser, String name, String lastNames, String phone) {
        this.idUser = idUser;
        this.name = name;
        this.lastNames = lastNames;
        this.phone = phone;
    }
    
    public User(String name, String lastNames, String phone) {
        this.name = name;
        this.lastNames = lastNames;
        this.phone = phone;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", name=" + name + ", lastNames=" + lastNames + ", phone=" + phone + '}';
    }
    
    
}
