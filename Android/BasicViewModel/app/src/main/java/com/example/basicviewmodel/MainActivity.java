package com.example.basicviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.basicviewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    ViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        model=new ViewModelProvider(this).get(ViewModel.class);
        activityMainBinding.button.setOnClickListener(v->{
            model.increaseCounter();
            activityMainBinding.textView.setText(""+model.getCounter());
        });
        activityMainBinding.textView.setText(""+model.getCounter());
    }
}