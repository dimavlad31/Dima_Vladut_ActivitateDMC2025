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

public class Activitate3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        if (intent.hasExtra("returnMessage") && intent.hasExtra("sum")) {
            String returnMessage = intent.getStringExtra("returnMessage");
            int sum = intent.getIntExtra("sum", 0);
            Toast.makeText(this, returnMessage + " Suma: " + sum, Toast.LENGTH_LONG).show();
        }
    }

    public void ButonApasat1Laborator3(View view){
        Intent intent = new Intent(this, Activitate4.class);
        Bundle bundle = new Bundle();
        bundle.putString("message", "Salut din Activitate3!");
        bundle.putInt("value1", 10);
        bundle.putInt("value2", 20);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String returnMessage = data.getStringExtra("returnMessage");
            int sum = data.getIntExtra("sum", 0);
            Toast.makeText(this, returnMessage + " Suma: " + sum, Toast.LENGTH_LONG).show();
        }
    }
}