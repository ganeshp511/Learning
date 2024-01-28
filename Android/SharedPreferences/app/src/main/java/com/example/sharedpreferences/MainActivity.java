package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.saveBtn);
        editText=findViewById(R.id.editTextText);
        textView=findViewById(R.id.displayView);

        //Initiaze
        sharedPreferences=getSharedPreferences("pref_data", Context.MODE_PRIVATE);
    //Here, "pref_data" is the name of the SharedPreferences file, and Context.MODE_PRIVATE indicates that only the calling application can read and write to the file.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString("username",editText.getText().toString());
            editor.apply();
            //store data

                String username=sharedPreferences.getString("username","Not found");
                //getting data
                textView.setText(username);
                /*Updating SharedPreferences:
                Updating values is similar to the writing process.

                // Example of updating SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("score", score + 10);
                editor.apply();

                This example updates the score value by adding 10 to the existing value.

                Removing from SharedPreferences:
                You can remove specific data using the remove method.

                // Example of removing from SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.apply();
                This example removes the "username" entry from the SharedPreferences file.

                Clearing SharedPreferences:
                To remove all data from the SharedPreferences file, you can use the clear method.

                // Example of clearing SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Remember to call apply() or commit() after making changes using the SharedPreferences.Editor to persist the changes. apply() is asynchronous and is preferred for performance reasons.

                Note: These examples assume that you are working within an Activity or a Fragment. If you are working in another context, you may need to adjust the way you obtain the SharedPreferences instance.
                */

            }
        });

    }
}