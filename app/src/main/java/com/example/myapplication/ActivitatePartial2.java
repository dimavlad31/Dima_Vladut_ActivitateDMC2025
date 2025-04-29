package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitatePartial2 extends AppCompatActivity {

    private EditText etMaterial, etLungime;
    private Spinner spinnerMarca;
    private CheckBox checkWireless;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_partial2);
        etMaterial = findViewById(R.id.dima_vlad_input_material);
        etLungime = findViewById(R.id.dima_vlad_input_lungime);
        spinnerMarca = findViewById(R.id.dima_vlad_input_marca);
        checkWireless = findViewById(R.id.dima_vlad_input_wireless);

        ArrayAdapter<HusaTelefon.marcaHusa>adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                HusaTelefon.marcaHusa.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarca.setAdapter(adapter);

        Button buttonSubmit = findViewById(R.id.dima_vlad_buton_act2);
        buttonSubmit.setOnClickListener(v -> salveazaTelefon());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    private void salveazaTelefon() {

        String material = etMaterial.getText().toString().trim();
        Double lungime = Double.parseDouble(etLungime.getText().toString().trim());
        HusaTelefon.marcaHusa marca = (HusaTelefon.marcaHusa) spinnerMarca.getSelectedItem();
        Boolean wireless = checkWireless.isChecked();
        Intent returnIntent = new Intent();

        HusaTelefon rezultat = new HusaTelefon(material,lungime,marca,wireless);

        returnIntent.putExtra("husaTelefon", rezultat);
        setResult(RESULT_OK,returnIntent);
        finish();
    }
    }
