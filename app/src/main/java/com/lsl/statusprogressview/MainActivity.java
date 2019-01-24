package com.lsl.statusprogressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private StatusProgressView mStatusProgressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStatusProgressView = findViewById(R.id.statusview);


//        mStatusProgressView.setStatuValues(new String[]{"1","2","3","4","5"});
//        mStatusProgressView.setCompleteState(4);
    }
}
