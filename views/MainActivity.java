package com.example.lucian.sqlite.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucian.sqlite.EncoderApp;
import com.example.lucian.sqlite.R;
import com.example.lucian.sqlite.encoder.Utils;
import com.example.lucian.sqlite.encoder.persistence.Persistence;
import com.example.lucian.sqlite.encoder.persistence.SQLite;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);

        EncoderApp.start(getBaseContext());

        SQLite sqlite = new SQLite("EncoderSQLite");

//        Utils.log(Persistence.class.getName());
//
//        Persistence.get("EncoderSQLite");
//
//        Utils.log(": " + ((String) Persistence.param("EncoderSQLite", "instance")));



//        SQLite sqlite = new SQLite("sqlite-alias");

//        Persistence.get("EncoderSQLite");

//        Encoder.start(getBaseContext());

//        Alguns Motivos que fazer o Java ser extremamente encapsulado (ruim):
//        A linguagem é feita pra estruturar objetos em sí, não hierarquia de classes;
//        Não é possível criar interfaces nem classes abstratas com métodos estáticos;
//        Não é possível criar uma hierarquia de classes com propriedades estáticas;
//        Não é possível obter a classe que referecia determinado método;
//        Não é possível efetuar o casting de pais para filhos;

        // apresentando números
//        Encoder.log("— EVENTS");
//        for (Map<String, String> record: Database.all("events")) {
//            // apresentamos os dados de todas as colunas
//            for (String column: record.keySet())
//                Encoder.log(column + ": " + record.get(column));
//            Encoder.log("—");
//        }



//        // salvando mdelos
//            Encoder.log("— SAVE");
//        for (int i = 0; i < 5; i++) {
//            Model resource = new Model();
//            resource.set("name", "Produto");
//            resource.set("date", Encoder.rand());
//            resource.set("description", Encoder.rand());
//            Encoder.log(resource.get("name"));
//            resource.save("events");
//        }

//        Event event: Model.all("events", Event.class)

        // mostrando models
//        Encoder.log("— ALL");
//            for (Model event: Model.all("events", Event.class)) {
//                Encoder.log(event.table());
//                Encoder.log(event.get("id"));
//                Encoder.log(event.get("name"));
//                Encoder.log("—");
//            }

//        Model event = new Event();
//        event.save();

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
