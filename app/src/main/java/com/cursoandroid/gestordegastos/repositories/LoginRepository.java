package com.cursoandroid.gestordegastos.repositories;

import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.User;

public class LoginRepository {

    public void saveUser(User user){
        SessionPersistence.saveUser(user);
    }
}
