package com.example.lucian.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucian.sqlite.encoder.Database;
import com.example.lucian.sqlite.encoder.Encoder;
import com.example.lucian.sqlite.encoder.Model;
import com.example.lucian.sqlite.models.Event;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        Encoder.start(getBaseContext());

        // salvando mdelos
//            Encoder.log("— SAVE");
//            for (int i = 0; i < 5; i++) {
//                Model resource = new Model();
//                resource.set("name", "Produto");
//                resource.set("date", Encoder.rand());
//                resource.set("description", Encoder.rand());
//                Encoder.log(resource.get("name"));
//                resource.save("events");
//            }

        // mostrando models
            Encoder.log("— ALL");
            for (Model event: Model.all("events")) {
                Encoder.log(event.get("id"));
                Encoder.log(event.get("name"));
                Encoder.log("—");
            }

//            Encoder.log("— NÚMEROS");
//            for (Map<String, String> record: Database.all("events")) {
//                // apresentamos os dados de todas as colunas
//                for (String column: record.keySet())
//                    Encoder.log(column + ": " + record.get(column));
//                Encoder.log("—");
//            }


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
