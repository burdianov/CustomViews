package com.crackncrunch.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TimerView mTimerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerView = (TimerView) findViewById(R.id.timer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTimerView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimerView.start();
    }
}
