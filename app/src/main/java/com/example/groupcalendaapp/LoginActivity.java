package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    private EditText emailTextEdited, passwordTextEdited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        SharedPreferences shPref = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);
        String emailText = shPref.getString("DefaultEmail","email@domain.com");
        emailTextEdited = (EditText) findViewById(R.id.loginText);
        passwordTextEdited = (EditText) findViewById(R.id.textPassword);
        emailTextEdited.setText(emailText);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor shPrefEdit = shPref.edit();
                shPrefEdit.putString("DefaultEmail",emailTextEdited.getText().toString());
                if(isValidEmailId(emailTextEdited.getText().toString().trim()) && passwordTextEdited.getText().toString().isEmpty() == false){
                    shPrefEdit.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    emailTextEdited.setText("Invalid Email or Password");
                    shPrefEdit.commit();
                }
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
    //email validation using regex
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}