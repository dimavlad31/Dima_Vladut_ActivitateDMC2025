package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activitate2 extends AppCompatActivity {
    private static final String TAG = "Activitate2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Log.v(TAG, "onCreate called");
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: Error log");
        Log.w(TAG, "onStart: Warning log");
        Log.d(TAG, "onStart: Debug log");
        Log.i(TAG, "onStart: Info log");
        Log.v(TAG, "onStart: Verbose log");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: Error log");
        Log.w(TAG, "onResume: Warning log");
        Log.d(TAG, "onResume: Debug log");
        Log.i(TAG, "onResume: Info log");
        Log.v(TAG, "onResume: Verbose log");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: Error log");
        Log.w(TAG, "onPause: Warning log");
        Log.d(TAG, "onPause: Debug log");
        Log.i(TAG, "onPause: Info log");
        Log.v(TAG, "onPause: Verbose log");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: Error log");
        Log.w(TAG, "onStop: Warning log");
        Log.d(TAG, "onStop: Debug log");
        Log.i(TAG, "onStop: Info log");
        Log.v(TAG, "onStop: Verbose log");
    }
}