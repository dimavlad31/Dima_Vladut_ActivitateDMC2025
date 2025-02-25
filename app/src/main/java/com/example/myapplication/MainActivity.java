package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void butonPushed2(View view) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(getString(R.string.Button_Pushed_string));
        TextView textView2 = findViewById(R.id.textView2);
        EditText firstNumber = findViewById(R.id.editTextText2);
        EditText SecondNumber = findViewById(R.id.editTextText3);
        String firstInput = firstNumber.getText().toString();
        String secondInput = SecondNumber.getText().toString();


        if (!firstInput.isEmpty() && !secondInput.isEmpty()) {
            try {

                int num1 = Integer.parseInt(firstInput);
                int num2 = Integer.parseInt(secondInput);
                int sum = num1 + num2;

                textView2.setText("Rezultat: " + sum);
            } catch (NumberFormatException e) {
                textView2.setText("Introduceți doar numere valide!");
            }
        } else {
            textView2.setText("Completați ambele câmpuri!");
        }

    }
}