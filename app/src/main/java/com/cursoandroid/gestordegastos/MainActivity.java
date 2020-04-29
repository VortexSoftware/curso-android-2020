package com.cursoandroid.gestordegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

import com.cursoandroid.gestordegastos.network.RestClient;

import io.paperdb.Paper;

public class MainActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
        RestClient.init();
    }
}
