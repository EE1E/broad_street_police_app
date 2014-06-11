package com.romanmitch.broad_street_police_app.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import java.util.Arrays;


public class settings extends ActionBarActivity {

    EditText[] text = new EditText[9];
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");//set activity title

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        shared = getSharedPreferences("A", Context.MODE_PRIVATE); // get the set of Preferences labeled "A"
        if(shared.getString("text_0", "").length() != 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm user")
                    .setMessage("Are you " + "\"" + String.valueOf(shared.getString("text_0", "") + "\"" + "?"))
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            text[0].setText("");
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        text[1] = (EditText) findViewById(R.id.btntxt1);
        text[2] = (EditText) findViewById(R.id.btntxt2);
        text[3] = (EditText) findViewById(R.id.btntxt3);
        text[4] = (EditText) findViewById(R.id.btntxt4);
        text[5] = (EditText) findViewById(R.id.btntxt5);
        text[6] = (EditText) findViewById(R.id.btntxt6);
        text[7] = (EditText) findViewById(R.id.btntxt7);
        text[8] = (EditText) findViewById(R.id.btntxt8);
        text[0] = (EditText) findViewById(R.id.username); // username
        final Button confirm = (Button)findViewById(R.id.confirm_settings);

       /* class Buttons {
            public boolean buttons()
            {
                text[0].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        confirm.setEnabled(!text[0].getText().toString().trim().isEmpty());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                return true;

                }
            }*/

        for (int i=0; i<9; i++) {
            if(shared.contains("text_"+i)) {
                text[i].setText(String.valueOf(shared.getString("text_"+i, "0")));  // get the value with the key  but return 0 if issue
            }
        }



/*        if(shared.contains("text_0"))
            text[0].setText(String.valueOf(shared.getString("text_0", "0")));  // get the value with the key  but return 0 if issue
        if(shared.contains("text_1"))
            text[1].setText(String.valueOf(shared.getString("text_1", "0")));  // get the value with the key  but return 0 if issue*/

/*        if ((!text[0].getText().toString().trim().isEmpty()))  //check that username field is not empty
            confirm.setEnabled(true);*/

/*        Buttons btn = new Buttons();
        btn.buttons();*/

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text[0].getText().toString().trim().length() != 0) {
                    if(text[1].getText().toString().trim().length() != 0 || text[2].getText().toString().trim().length() != 0  || text[3].getText().toString().trim().length() != 0 || text[4].getText().toString().trim().length() != 0 || text[5].getText().toString().trim().length() != 0 || text[6].getText().toString().trim().length() != 0 || text[7].getText().toString().trim().length() != 0  || text[8].getText().toString().trim().length() != 0) {

                        final SharedPreferences.Editor editor = shared.edit();
                        String[] fields_text = new String[text.length];
                        for (int i = 0; i < text.length; i++) {
                            fields_text[i] = text[i].getText().toString();
                            editor.putString("text_" + i, fields_text[i]);
                            editor.commit();

                        }
                        Intent intent = new Intent(settings.this, buttons_screen.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please enter a button label",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter a username ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Intents for items in options menu
        switch (item.getItemId()) {
            case R.id.clear:
                for (int i=1; i<9; i++) {
                        text[i].setText("");  // get the value with the key  but return 0 if issue
                    }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}