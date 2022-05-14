package com.example.yogaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    int[] newArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        newArray = new int[]{
                R.id.boat_pose, R.id.boat2_pose,R.id.boat3_pose,R.id.boat4_pose,R.id.boat5_pose,
                R.id.boat6_pose,R.id.boat7_pose,R.id.boat8_pose,R.id.boat9_pose,
        };
    }

    public void Imagebuttonclicked(View view) {


        for(int i = 0; i < newArray.length; i++){

            if(view.getId() == newArray[i]){
                int value = i+1;
                Log.i("FIRST", String.valueOf(value));
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("value", String.valueOf(value));
                startActivity(intent);
            }
        }

    }
}