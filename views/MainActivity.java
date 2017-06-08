package com.example.lucian.sqlite.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucian.sqlite.EncoderApp;
import com.example.lucian.sqlite.R;
import com.example.lucian.sqlite.encoder.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        EncoderApp.start(getBaseContext());

        // Iniciar serviço
        // Intent serviceIntent = new Intent(this, NotificationService.class);
        // startService(serviceIntent);

        // Vinculando um fragmento
        // if (savedInstanceState == null) {
        //    getSupportFragmentManager().beginTransaction().add(R.id.headerFragment, new HeaderFragment()).commit();
        // }

        // Mostra um diálogo simples imediatamente
        // runOnUiThread(new Runnable() {
        //     @Override
        //     public void run() {
        //         if (!isFinishing())
        //             Message.get(MainActivity.this).show();
        //     }
        // });

        // insere um fragmento na activity
        // if (savedInstanceState == null) {
        //    getSupportFragmentManager().beginTransaction().add(R.id.headerFragment, new HeaderFragment()).commit();
        // }

        // Criar notificação
        // Message.notification(this, "Título", "Texto");

        Utils.log("END");



        // tentativa de passar parametros para o fragmento
//        if (savedInstanceState == null) {
//            HeaderFragment headerFragment = new HeaderFragment();
//            Bundle params = new Bundle();
//            params.putString("title", "test");
//            headerFragment.setArguments(params);
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.headerFragment, new HeaderFragment()).commit();
//        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        Utils.log("Criando um menu a partir de um XML.");
//        getMenuInflater().inflate(R.menu.resource_navigation, menu);
//        return true;
//    }

}
