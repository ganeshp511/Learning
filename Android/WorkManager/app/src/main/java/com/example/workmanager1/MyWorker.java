package com.example.workmanager1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    //doWork runs asyncronously in background on Background Thread provided by WorkManager
    @NonNull
    @Override
    public Result doWork() {
        //getting data from inputdata
        Data data= getInputData();
        int countingLimit=data.getInt("max_limit",0);
        for (int i = 0; i < countingLimit; i++) {
            //Toast.makeText(getApplicationContext(), "Count is"+i, Toast.LENGTH_SHORT).show();
            Log.i("workmanager", "doWork: count is: "+i);
        }

        //sending data and done notify
        Data dataToSend=new Data.Builder().putString("msg","Task done Successfully").build();
        return Result.success(dataToSend);
        //work succedded or not
    }
}
