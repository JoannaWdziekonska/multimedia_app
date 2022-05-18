package com.example.yogaapp;

import static java.net.InetAddress.getLocalHost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SignUp extends AppCompatActivity {

    TextInputEditText tietFirstName, tietLastName, tietUserName, tietEmail, tietPassword;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tietFirstName = findViewById(R.id.firstname);
        tietLastName = findViewById(R.id.lastname);
        tietUserName = findViewById(R.id.username);
        tietEmail = findViewById(R.id.email);
        tietPassword = findViewById(R.id.password);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(v -> {
            Intent intent =  new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });

        buttonSignUp.setOnClickListener(view -> {
            String firstname, lastname, username, email, password;
            firstname = String.valueOf(tietFirstName.getText());
            lastname = String.valueOf(tietLastName.getText());
            username = String.valueOf(tietUserName.getText());
            email = String.valueOf(tietEmail.getText());
            password = String.valueOf(tietPassword.getText());

            if (!firstname.equals("") && !lastname.equals("") && !username.equals("") && !email.equals("") && !password.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[5];
                        field[0] = "firstname";
                        field[1] = "lastname";
                        field[2] = "username";
                        field[3] = "email";
                        field[4] = "password";
                        String[] data = new String[5];
                        data[0] = firstname;
                        data[1] =lastname;
                        data[2] = username;
                        data[3] = email;
                        data[4]= password;
                        InetAddress addr = null;

                        //String url = String.format("http://localhost/LoginRegister/signup.php", addr.getHostAddress());
                        PutData pdata = new PutData("http://192.168.7.11/LoginRegister/signup.php", "POST", field, data);
                        if (pdata.startPut()) {
                            if (pdata.onComplete()) {
                                progressBar.setVisibility(View.GONE);
                                String result = pdata.getResult();
                                if (result.equals("Sign Up Success")){
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
            }
        });

    }
}