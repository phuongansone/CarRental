/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import enums.Role;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author PC
 */
public class UserDTO implements Serializable {
    private String email;
    private String phone;
    private String name;
    private String address;
    private String password;
    private RoleDTO role;
    private UserStatusDTO userStatus;
    private Date createDate;

    public UserDTO() {
    }

    public UserDTO(String email, String phone, String name, String address, 
            String password, RoleDTO role, UserStatusDTO userStatus, Date createDate) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public UserStatusDTO getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusDTO userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public boolean isAdmin() {
        return this.role.getId() == Role.ADMIN.roleId;
    }
    
    public boolean isUser() {
        return this.role.getId() == Role.USER.roleId;
    }
}
