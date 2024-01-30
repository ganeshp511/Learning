package com.example.batterychargenotifier;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

public class BatteryChargeNotifierService extends Service {
    private static final String CHANNEL_ID = "ForegroundServiceChannel";
    private BroadcastReceiver batteryReceiver;
    private MediaPlayer mediaPlayer;

    /*@Override
    public void onCreate() {
        super.onCreate();

    }*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForegroundService();
        registerBatteryReceiver();
        return START_STICKY; // Service will be restarted if terminated by the system
    }
    private void startForegroundService() {
        createNotificationChannel();

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("BatteryChargeNotifierService")
                    .setContentText("Running...")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build();
        }

        startForeground(1, notification);
        Toast.makeText(this, "BatteryChargeNotifierService started", Toast.LENGTH_SHORT).show();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    private void registerBatteryReceiver() {
        batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                int batteryPercentage = (level * 100) / scale;

                if (batteryPercentage >= 95) {
                    ringSystemRingtone();
                }
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }

    private void ringSystemRingtone() {
        // Add code to ring system ringtone here
        // For example, you can use AudioManager or RingtoneManager
        // Note: Ensure to handle the required permissions
        mediaPlayer= MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
        //create mediaplayer instance and setting default system ringtone
        mediaPlayer.setLooping(true);
        //play on loop
        mediaPlayer.start();

    }
    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(BatteryChargeNotifierService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(!foregroundServiceRunning()) {
            Intent serviceIntent = new Intent(this,
                    BatteryChargeNotifierService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
        mediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
