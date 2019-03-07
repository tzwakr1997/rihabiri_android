package com.example.migita.daire_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Mode_SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode__select);


            ImageButton button = findViewById(R.id.mesurement);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Mode_SelectActivity.this, Rehabilitation_SelectActivity.class);
                    intent.putExtra("KEY",1);
                    intent.putExtra("username","username");
                    startActivity(intent);
                }
            });

//            ImageButton button2 = findViewById(R.id.practice);
//            button2.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Mode_SelectActivity.this, Rehabilitation_SelectActivity.class);
//                    intent.putExtra("KEY",2);
//                    intent.putExtra("username","username");
//                    startActivity(intent);
//                }
//            });


            ImageButton button1 = findViewById(R.id.record);
            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Mode_SelectActivity.this, GraphActivity.class);
                    intent.putExtra("username","username");
                    startActivity(intent);
                }
            });
        }
    }

