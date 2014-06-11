package com.romanmitch.broad_street_police_app.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;


public class buttons_screen extends ActionBarActivity {

    static String button1;
    static String button2;
    static String button3;
    static String button4;
    static String button5;
    static String button6;
    static String button7;
    static String button8;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_screen);

        final Button[] btn = new Button[8];

        btn[0] = (Button)findViewById(R.id.button1);
        btn[1] = (Button)findViewById(R.id.button2);
        btn[2] = (Button)findViewById(R.id.button3);
        btn[3] = (Button)findViewById(R.id.button4);
        btn[4] = (Button)findViewById(R.id.button5);
        btn[5] = (Button)findViewById(R.id.button6);
        btn[6] = (Button)findViewById(R.id.button7);
        btn[7] = (Button)findViewById(R.id.button8);

        shared = getSharedPreferences("A", Context.MODE_PRIVATE);

        setTitle(shared.getString("text_0", "")); //set activity title

        for (int i=0; i<btn.length; i++){
            btn[i].setText(shared.getString("text_"+(i+1), ""));
            if (btn[i].getText().toString().length() != 0)
                btn[i].setVisibility(View.VISIBLE);

        }
        //Code to respond to button clicks

        for (int i=0; i<btn.length; i++){ //this for loop sets onClick() responses to each button click
            final int ii = i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Click "+ii+"!",Toast.LENGTH_SHORT).show();
                }
            });
        }

        /*btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 1!",Toast.LENGTH_SHORT).show();
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 2!",Toast.LENGTH_SHORT).show();
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 3!",Toast.LENGTH_SHORT).show();
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 4!",Toast.LENGTH_SHORT).show();
            }
        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 5!",Toast.LENGTH_SHORT).show();
            }
        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 6!",Toast.LENGTH_SHORT).show();
            }
        });
        btn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 7!",Toast.LENGTH_SHORT).show();
            }
        });
        btn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click 8!",Toast.LENGTH_SHORT).show();

            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buttons_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.:
                //Toast.makeText(getApplicationContext(), "Settings",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        return super.onOptionsItemSelected(item);
    }
}
