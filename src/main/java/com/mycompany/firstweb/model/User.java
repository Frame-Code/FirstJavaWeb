
package com.mycompany.firstweb.model;

/**
 *
 * @author Artist-Code
 */
public class User {
    private Long id;
    private String name;
    private String lastNames;
    private String phone;

    public User() {
    }
    public User(Long id, String name, String lastNames, String phone) {
        this.id = id;
        this.name = name;
        this.lastNames = lastNames;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "User{" + "id=" + id + ", name=" + name + ", lastNames=" + lastNames + ", phone=" + phone + '}';
    }
    
    
}
