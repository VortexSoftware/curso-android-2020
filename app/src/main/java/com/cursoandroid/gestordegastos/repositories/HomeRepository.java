package com.cursoandroid.gestordegastos.repositories;

import com.cursoandroid.gestordegastos.helpers.SessionPersistence;
import com.cursoandroid.gestordegastos.models.User;

public class HomeRepository {

    public User getUser() {
        return SessionPersistence.getUser();
    }
}
