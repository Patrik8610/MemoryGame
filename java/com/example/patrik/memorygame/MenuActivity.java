package com.example.patrik.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        definebutton();

        }
        public  void definebutton(){

        findViewById(R.id.button_4x4).setOnClickListener(buttonClickListener);
        findViewById(R.id.button6x6).setOnClickListener(buttonClickListener);

        }
        private View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.button_4x4:
                        Intent Game4x4 = new Intent(MenuActivity.this, Game4x4Activity.class);
                        startActivity(Game4x4);
                        break;

                    case R.id.button6x6:
                        Intent Game6x6 = new Intent(MenuActivity.this, Game6x6Activity.class);
                        startActivity(Game6x6);
                        break;


                }

            }
        };
    }

