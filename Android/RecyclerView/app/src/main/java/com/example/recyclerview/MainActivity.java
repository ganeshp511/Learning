package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Model> data= new ArrayList<>();
        data.add(new Model("item1","item2"));
        data.add(new Model("item1","item2"));

        MyAdapter myAdapter= new MyAdapter(getApplicationContext(),data);
        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setAdapter(myAdapter);
        // Optionally, set a layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}