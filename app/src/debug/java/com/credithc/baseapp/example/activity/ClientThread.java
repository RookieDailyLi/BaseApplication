package com.credithc.baseapp.example.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

public class ClientThread extends Thread {
    Handler serverHandler;
    Handler clientHandler;

    public ClientThread(@NonNull String name) {
        super(name);
    }

    public void setServerHandler(Handler serverHandler) {
        this.serverHandler = serverHandler;
    }

    public Handler getHandler() {
        while (clientHandler == null) {
        }
        return clientHandler;
    }

    @Override
    public void run() {
        Looper.prepare();
        clientHandler = new ClientThread.ClientHandler();
        while (serverHandler == null) {

        }
        for (int i = 0; i < 5; i++) {
            Message msg = serverHandler.obtainMessage(0, "xxx " + i + " from " + Thread.currentThread().getName());
            serverHandler.sendMessage(msg);
        }
        Looper.loop();


    }

    class ClientHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("Thread #(" + Thread.currentThread().getName() + ") received msg, " + msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
