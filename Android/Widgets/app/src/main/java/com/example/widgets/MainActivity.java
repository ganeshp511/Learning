package com.example.widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ProgressBar progressBar;
    String data[] ={"abc","sds","sds","wew"};
    ArrayAdapter arrayAdapter;
    Button button;
    int count=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.spinner);
        button=findViewById(R.id.button);
        progressBar=findViewById(R.id.progressBar2);
        arrayAdapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,data);
        spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(v->{
            count=count+count;
            progressBar.setProgress(count);
        });



    }
}