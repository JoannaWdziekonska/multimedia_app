package com.example.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yogaapp.util.EspressoIdlingResource;
import com.google.android.material.textfield.TextInputEditText;

import java.net.InetAddress;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView texViewSignUp;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        texViewSignUp =  findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        texViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent =  new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  username, password;

                username = String.valueOf(textInputEditTextUsername.getText());

                password = String.valueOf(textInputEditTextPassword.getText());

                if (!username.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = username;
                            data[1]= password;
                            InetAddress addr = null;

                            EspressoIdlingResource.increment();
                           // String url = String.format("http://localhost/LoginRegister/login.php", addr.getHostAddress());
                            PutData pdata = new PutData("http://10.1.237.95/LoginRegister/login.php", "POST", field, data);
                            if (pdata.startPut()) {
                                if (pdata.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = pdata.getResult();
                                    EspressoIdlingResource.decrement();
                                    if (result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), Splashscreenok.class);
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
            }
        });




    }
}