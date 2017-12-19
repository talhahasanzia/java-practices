package com.playground.streamapidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);


        // get serialized data

        DataModel receivedData= (DataModel) getIntent().getSerializableExtra("data");


        Log.d("SAMPLE", "onCreate: "+receivedData.toString());
    }
}
