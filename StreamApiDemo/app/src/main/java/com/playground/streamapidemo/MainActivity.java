package com.playground.streamapidemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Stream API is not supported until API level 24.
 * <p>
 * Other features of JAva 8 works on all platforms.
 */
public class MainActivity extends AppCompatActivity implements InstanceChecker<MainActivity> {

    TextView sampleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleText = findViewById(R.id.sample_textview);

        sampleText.setMovementMethod(new ScrollingMovementMethod());

        lambdaSample();

        Log.d("Main", "onCreate: " + instanceOf(this));  // default methods in interface


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Serialization example

/*
        DataModel dataModel = new DataModel(5575355089l, "Some Data");

        Intent intent = new Intent(MainActivity.this, SampleActivity.class);

        intent.putExtra("data", dataModel);

        startActivity(intent);*/
    }

    /**  Java 8 streams **/


    /**
     * Preliminaries : Java 8 specific topics
     **/

    String resultString = "Unsorted:\n";


    void lambdaSample() {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");


        names.stream().forEach((String name) -> resultString += name + "\n");  // lambda + stream


        Collections.sort(names);


        resultString += "\n\nSorted:\n\n";

        names.stream().forEach((String name) -> resultString += name + "\n");  // lambda + stream

        sampleText.setText(resultString);

    }


}
