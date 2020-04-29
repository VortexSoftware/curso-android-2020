package com.cursoandroid.gestordegastos.network.responses;

import com.cursoandroid.gestordegastos.models.User;

public class LoginResponse {
    private User user;
    private String error;

    public User getUser() {
        return user;
    }

    public String getError() {
        return error;
    }
}
