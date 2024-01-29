package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //context is app context, intent contains info about intent receivd from system
        //getAction() -> returns string action of intent
        // check received intent's action is airplanemode changed
        //receives state boolean from intent , default false
        //register broadcastreceiver in manifest
        if(intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
            boolean isAirplaneModeOn=intent.getBooleanExtra("state",false);
            String msg = isAirplaneModeOn ? "Airplane mode is on" : "Airplane mode is off";
            //if true then yes else false
            Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
        }
    }
}
