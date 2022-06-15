package com.example.yogaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity2 extends AppCompatActivity {

    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    boolean MTimeRunning;
    long MTimeLeftinmillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");


        int intvalue = Integer.valueOf(buttonvalue);


        switch(intvalue){

            case 1:
                setContentView(R.layout.activity_sidestretch);
                break;
            case 2:
                setContentView(R.layout.activity_boundangle);
                break;
            case 3:
                setContentView(R.layout.activity_crow);
                break;
            case 4:
                setContentView(R.layout.activity_catcow);
                break;
            case 5:
                setContentView(R.layout.activity_eagle);
                break;
            case 6:
                setContentView(R.layout.activity_forwardbend);
                break;
            case 7:
                setContentView(R.layout.activity_runnerslunge);
                break;
            case 8:
                setContentView(R.layout.activity_sideplank);
                break;
            case 9:
                setContentView(R.layout.activity_treepose);
                break;
        }

        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.time);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MTimeRunning){
                    stoptimer();
                }else{
                    startTimer();
                }
            }
        });


    }

    void stoptimer(){
        countDownTimer.cancel();
        MTimeRunning = false;
        startBtn.setText("START");
    }

    void startTimer(){
        final CharSequence value1 = mtextview.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);



        final int number = Integer.valueOf(num2) * 60  + Integer.valueOf(num3);
        MTimeLeftinmillis = number * 1000;



        countDownTimer = new CountDownTimer(MTimeLeftinmillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                MTimeLeftinmillis = millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {

                int newvalue = Integer.valueOf(buttonvalue) + 1;
                if(newvalue <= 7){
                    Intent intent = new Intent(ThirdActivity2.this, ThirdActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newvalue));
                    startActivity(intent);

                }
                else{

                    newvalue = 1;
                    Intent intent = new Intent(ThirdActivity2.this, ThirdActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newvalue));
                    startActivity(intent);
                }

            }
        }.start();
        startBtn.setText("Pause");
        MTimeRunning = true;


    }

    private void updateTimer(){
        int minutes = (int) MTimeLeftinmillis/60000;
        int seconds = (int) MTimeLeftinmillis%60000/1000;


        String timeLeftText="";
        if(minutes<10){
            timeLeftText="0";

        }
        timeLeftText = timeLeftText + minutes + ":";
        if(seconds<10)
            timeLeftText+="0";
        timeLeftText+=seconds;
        mtextview.setText(timeLeftText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}