package com.example.lucian.sqlite.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucian.sqlite.EncoderApp;
import com.example.lucian.sqlite.R;
import com.example.lucian.sqlite.encoder.Model;
import com.example.lucian.sqlite.encoder.Utils;
import com.example.lucian.sqlite.encoder.persistence.Persistence;
import com.example.lucian.sqlite.encoder.persistence.SQLite;
import com.example.lucian.sqlite.models.Event;
import com.example.lucian.sqlite.services.NotificationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        EncoderApp.start(getBaseContext());

        Intent serviceIntent = new Intent(this, NotificationService.class);

        startService(serviceIntent);




        Utils.log("END");

// tentativa de passar parametros para o fragmento
//        if (savedInstanceState == null) {
//            HeaderFragment headerFragment = new HeaderFragment();
//            Bundle params = new Bundle();
//            params.putString("title", "test");
//            headerFragment.setArguments(params);
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.headerFragment, headerFragment).commit();
//        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        Encoder.log("Criando um menu a partir de um XML.");
//        getMenuInflater().inflate(R.menu.resource_navigation, menu);
//        return true;
//    }

}
