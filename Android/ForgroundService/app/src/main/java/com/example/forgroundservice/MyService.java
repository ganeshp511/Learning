package com.example.forgroundservice;

import static android.os.Build.ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;

import java.net.IDN;

public class MyService extends Service {
    MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
        // returns null means not bounding any component to service
        //  throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer=MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
        //create mediaplayer instance and setting default system ringtone
        mediaPlayer.setLooping(true);
        //play on loop
        mediaPlayer.start();
        //Notification Notification = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel("ID","Notification", NotificationManager.IMPORTANCE_LOW);
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
            Notification.Builder builder=new Notification.Builder(this,"ID").setContentText("Service running").setContentTitle("Service enabled").setSmallIcon(R.drawable.ic_launcher_background);
            startForeground(1001,builder.build());

        }

        //The reason we need to provide a notification for a Foreground Service is because we need to let the user know that there is a service from our app that is running even when the app is terminated.
        // The notification cannot be removed until the service is terminated.
        //start it
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}