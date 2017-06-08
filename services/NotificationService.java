package com.example.lucian.sqlite.services;

import android.app.IntentService;
import android.content.Intent;

import com.example.lucian.sqlite.encoder.Utils;

public class NotificationService extends IntentService {

    private boolean running = true;

    public NotificationService() {
        super("Notification Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            for (int i = 10; i <= 100; i+=10) {
                Utils.log("uploading: " + i + "%");

                try {
                    wait(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(!running) break;
            }
        }
    }

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    }

}
