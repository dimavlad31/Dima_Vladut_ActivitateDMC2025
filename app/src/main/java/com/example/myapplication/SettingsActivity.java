package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText etTextSize;
    private Spinner spinnerTextColor;
    private Button btnSave;

    private static final String[] colorNames = {
            "Negru", "Roșu", "Verde", "Albastru", "Mov", "Portocaliu", "Galben", "Gri", "Roz", "Turcoaz"
    };

    private static final String[] colorHexCodes = {
            "#000000", "#FF0000", "#00FF00", "#0000FF", "#800080",
            "#FFA500", "#FFFF00", "#808080", "#FFC0CB", "#40E0D0"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etTextSize = findViewById(R.id.et_text_size);
        spinnerTextColor = findViewById(R.id.spinner_text_color);
        btnSave = findViewById(R.id.btn_save_settings);

        // Populăm spinnerul cu numele culorilor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, colorNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextColor.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String sizeText = etTextSize.getText().toString().trim();

            if (!sizeText.isEmpty()) {
                try {
                    float size = Float.parseFloat(sizeText);
                    int selectedIndex = spinnerTextColor.getSelectedItemPosition();
                    String selectedColorHex = colorHexCodes[selectedIndex];

                    SharedPreferences preferences = getSharedPreferences("UserSettings", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putFloat("textSize", size);
                    editor.putString("textColor", selectedColorHex);
                    editor.apply();

                    Toast.makeText(this, "Setări salvate!", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Dimensiune invalidă!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Introdu dimensiunea textului!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
