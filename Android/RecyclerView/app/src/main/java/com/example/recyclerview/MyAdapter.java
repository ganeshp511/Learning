package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {
    Context context;
    List <Model> data;
    Model model;
    public MyAdapter(Context context, List<Model> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creates new viewHolder
        View view= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        // Binds data to ViewHolder
        Model model = data.get(position);
        holder.textView1.setText(model.getItem1());
        holder.textView2.setText(model.getItem2());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public TextView textView1;
        public TextView textView2;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.textView1);
            textView2=itemView.findViewById(R.id.textView2);



        }
    }
}
