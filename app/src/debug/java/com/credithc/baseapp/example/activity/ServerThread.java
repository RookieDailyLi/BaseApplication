package com.credithc.baseapp.example.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

public class ServerThread extends Thread {
    Handler clientHandler;
    Handler serverHandler;

    public ServerThread(@NonNull String name) {
        super(name);
    }

    public void setClientHandler(Handler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public Handler getHandler() {
        while (serverHandler == null) {
        }
        return serverHandler;
    }

    @Override
    public void run() {
        Looper.prepare();
        serverHandler = new ServerThread.ServerHandler();
        while (clientHandler == null) {

        }
        for (int i = 0; i < 5; i++) {
            Message msg = clientHandler.obtainMessage(0, "xxx " + i + " from " + Thread.currentThread().getName());
            clientHandler.sendMessage(msg);
        }
        Looper.loop();
    }

    class ServerHandler extends Handler {
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
