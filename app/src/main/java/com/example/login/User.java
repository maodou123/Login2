package com.example.login;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/4/25.
 */

public class User extends DataSupport{
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
