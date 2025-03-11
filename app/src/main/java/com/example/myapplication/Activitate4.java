package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activitate4 extends AppCompatActivity {
    int value1, value2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String message = extras.getString("message");
            value1 = extras.getInt("value1");
            value2 = extras.getInt("value2");

            String toastMessage = message + " Valori: " + value1 + " si " + value2;
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
        }
    }

    public void ButonApasat2Laborator3(View view){
        Intent intent = new Intent();
        intent.putExtra("returnMessage", "Inapoi din Activitate4");
        intent.putExtra("sum", value1 + value2);
        setResult(RESULT_OK, intent);
        finish();
    }


}