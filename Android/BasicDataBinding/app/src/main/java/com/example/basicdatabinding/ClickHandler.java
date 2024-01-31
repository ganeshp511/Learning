package com.example.basicdatabinding;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.basicdatabinding.databinding.ActivityMainBinding;

public class ClickHandler extends ViewModel {
    public void onClickHandler(View view){
        Toast.makeText(view.getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();

    }
}
