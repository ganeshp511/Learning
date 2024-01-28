package com.example.forgroundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startBtn, stopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check if the service is not running first. If it is not running, we want to start the service.
        if(!foregroundServiceRunning()) {
            Intent serviceIntent = new Intent(this,
                    MyService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent);
            }
        }

        startBtn=findViewById(R.id.startBtn);
        stopBtn=findViewById(R.id.stopBtn);
        //adding setOnClickListener on Button, but this method is having interface in its argument(callback) and this method is having onClick abstract method which
        //we need to override, so we can create a class and implement OnCLickListener interface then override onClick there.
        startBtn.setOnClickListener(v -> {
            //start MyService class
            Intent intent= new Intent(getApplicationContext(),MyService.class);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                startForegroundService(intent);
                /*Intent intent = new Intent(this, MyIntentService.class);
                startService(intent);*/
                //Keep in mind that IntentService is deprecated starting from API level 30, and it's recommended to use JobIntentService or JobService for background tasks. If possible, consider migrating to JobIntentService for a more modern approach.

            }

            //startForegroundService() only starts the service, we still need to put it in the Foreground state.

        });
        stopBtn.setOnClickListener(v->{
            Intent intent= new Intent(getApplicationContext(),MyService.class);
            stopService(intent);
        });
    }
    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(MyService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}