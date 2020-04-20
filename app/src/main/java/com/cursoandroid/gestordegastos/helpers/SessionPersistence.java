package com.cursoandroid.gestordegastos.helpers;

import com.cursoandroid.gestordegastos.models.User;

import io.paperdb.Paper;

public class SessionPersistence {

    public static void saveUser(User user){
        Paper.book().write("user",user);
    }

    public static User getUser(){
        return Paper.book().read("user");
    }

    public static void deleteUser(){
        Paper.book().delete("user");
    }

}
