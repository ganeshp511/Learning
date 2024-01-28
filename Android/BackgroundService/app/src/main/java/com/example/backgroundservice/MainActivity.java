package com.example.backgroundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.serviceexample.R;

public class MainActivity extends AppCompatActivity {
    Button startBtn, stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn=findViewById(R.id.startBtn);
        stopBtn=findViewById(R.id.stopBtn);
        //adding setOnClickListener on Button, but this method is having interface in its argument(callback) and this method is having onClick abstract method which
        //we need to override, so we can create a class and implement OnCLickListener interface then override onClick there.
        startBtn.setOnClickListener(v -> {
            //start MyService class
            Intent intent= new Intent(getApplicationContext(),MyService.class);
            startService(intent);

        });
        stopBtn.setOnClickListener(v->{
            Intent intent= new Intent(getApplicationContext(),MyService.class);
            stopService(intent);
        });
    }
}