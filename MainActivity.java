package com.example.stopwatchapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStart, btnPause, btnReset;

    private Handler handler = new Handler();
    private int seconds = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI elements to Java variables
        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);

        // Start button click listener
        btnStart.setOnClickListener(v -> {
            isRunning = true;
            runTimer();
        });

        // Pause button click listener
        btnPause.setOnClickListener(v -> isRunning = false);

        // Reset button click listener
        btnReset.setOnClickListener(v -> {
            isRunning = false;
            seconds = 0;
            updateTimer();
        });
    }

    // Method to update the timer every second
    private void runTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    seconds++;
                }
                updateTimer();
                handler.postDelayed(this, 1000);
            }
        });
    }

    // Method to format and display the timer
    private void updateTimer() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        tvTimer.setText(time);
    }
}
