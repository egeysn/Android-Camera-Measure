package com.mycameraalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    /**
     Copyright 23 Kasım Cumartesi 2019

     YASİN EGE
     */

    private float heightDevice;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor ;
    private EditText myEditText;

    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = findViewById(R.id.edittextH);



         pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); // 0 - for private mode
          editor = pref.edit();


        Log.e("MainActivity","Sending password to Firebase:");

        if (pref.contains("heightDepo")) {
            myEditText.setText(pref.getString("heightDepo","Enter telephone H(Meters)"));
        }


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SensorActivity.class);

                String heightString = myEditText.getText().toString().trim() ;
                heightDevice = Float.parseFloat(heightString);

                editor.putString("heightDepo",heightString);
                editor.apply();

                intent.putExtra("height",heightDevice);
                startActivity(intent);
            }
        });
    }
}
