package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if ur targetapi is  api26 i.e. oreo we need to use dynamic broadcast i.e. generate theough java file
        IntentFilter intentFilter=new IntentFilter("android.intent.action.AIRPLANE_MODE");
        //create intentfilter obj and register
        AirplaneModeReceiver receiver=new AirplaneModeReceiver();
        registerReceiver(receiver,intentFilter);
    }
}