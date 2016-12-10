package com.xc.as.core.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yxc on 2016/12/11.
 */

@Document(collection = "AdminUser")
public class AdminUser {
    private String id;

    private String user;

    private String password;

    private int role;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
