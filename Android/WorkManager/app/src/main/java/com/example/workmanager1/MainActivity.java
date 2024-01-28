package com.example.workmanager1;

import static com.example.workmanager1.R.id.button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
/*
*   1. Creates an instance of WorkRequest using OneTimeWorkRequest.Builder.
*   MyWorker.class defines the work to be done in the background.
*
*   2. Sets a click listener on the button. When the button is clicked, it uses the WorkManager
*   class to enqueue the workRequest. This means it schedules the defined background work to be executed.
*
*   3. create constraints instance and configured as task to execute when charging
*
*   4. providing necessory input data to worker.
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =findViewById(R.id.button);

        //data
        Data data = new Data.Builder().putInt("max_limit", 500).build();

        //constraints
        Constraints constraints = new Constraints.Builder().
                setRequiresCharging(true).
                build();

        // note: new abc.Builder() creates new instance this helps to do other work
        //creating instance of workrequest
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).setInputData(data).build();
        //enque req with workmanager
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
                //getting workmanager instance and enqueing our workrequest to it
            }
        });

        //monitoring status of work manager
        //transfering data from worker to activity -> status
        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(workRequest.getId()).observe(this,
                new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if(workInfo!=null){
                            Toast.makeText(MainActivity.this, "Status: "+workInfo.getState().name(), Toast.LENGTH_SHORT).show();

                        if(workInfo.getState().isFinished()){
                            Data data1=workInfo.getOutputData();
                            Toast.makeText(MainActivity.this, ""+data1.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                        }
                    }
                });
    }
}