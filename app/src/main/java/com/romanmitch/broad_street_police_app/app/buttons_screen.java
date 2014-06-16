package com.romanmitch.broad_street_police_app.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.makemyandroidapp.googleformuploader.GoogleFormUploader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class buttons_screen extends ActionBarActivity {


    // GPSTracker class
    GPS gps;
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
        final Button btnz = (Button) findViewById(R.id.btnz);

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
        btnz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons_screen.this.finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }


        });

        for (int i=0; i<btn.length; i++){ //this for loop sets onClick() responses to each button click
            final int ii = i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    gps = new GPS(buttons_screen.this);
                    if (gps.canGetLocation()) {
                        if (((gps.getLatitude() == 0.0) && (gps.getLongitude() == 0.0))) {
                            Toast.makeText(getApplicationContext(), "No GPS yet, please try again",
                                    Toast.LENGTH_LONG).show();

                        } else {



                            double latitude = gps.getLatitude();
                            double longitude = gps.getLongitude();
                            StringBuilder sb = new StringBuilder();
                            Geocoder gc = new Geocoder(buttons_screen.this, Locale.getDefault());
                            try {
                                List<Address> addresses = gc.getFromLocation(latitude, longitude, 5);

                                if (addresses.size() > 0) {
                                    Address address = addresses.get(0);
                                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                                        sb.append(address.getAddressLine(i)).append("\n");


                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            GoogleFormUploader uploader = new GoogleFormUploader("1A2swvqW_akwg4aWL3-6FPJExVT2kpC0hb6pMOXx_PJc");
                            uploader.addEntry("2058901428", shared.getString("text_0", ""));
                            try {
                                uploader.addEntry("755055969", URLEncoder.encode(String.valueOf(btn[ii].getText()), "UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            uploader.addEntry("1963076528", String.valueOf(gps.getLatitude()));
                            uploader.addEntry("322839136", String.valueOf(gps.getLongitude()));
                            uploader.addEntry("493297396", " "+sb.toString());
                            uploader.upload();


                            Toast.makeText(getApplicationContext(), "Sent",
                                    Toast.LENGTH_LONG).show();

                            // \n is for new line
                        }


                    } else {
                        // can't get location
                        // GPS or Network is not enabled
                        // Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }

                    //Toast.makeText(getApplicationContext(), "Click "+ii+"!",Toast.LENGTH_SHORT).show();


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
        Intent intent1 = new Intent(buttons_screen.this, web.class);
        final SharedPreferences.Editor editor = shared.edit();
        switch (item.getItemId()) {
            case R.id.action_settings: {
                finish();
                break;
            }
            case R.id.map_page: {
                editor.putString("web", "map");
                editor.commit();
                startActivity(intent1);
                break;
            }
            case R.id.spreadsheet_page: {
                editor.putString("web", "spreadsheet");
                editor.commit();
                startActivity(intent1);
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }



}
