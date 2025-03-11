package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activitate2Lab4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate2_lab4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<ViDeoProiector.TipProiector> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ViDeoProiector.TipProiector.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerTipProiector = findViewById(R.id.spinner_tip_proiector);
        spinnerTipProiector.setAdapter(adapter);

        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(v -> {
            String marca = ((EditText) findViewById(R.id.input_marca)).getText().toString();
            int rezolutie = Integer.parseInt(((EditText) findViewById(R.id.input_rezolutie)).getText().toString());
            double luminozitate = Double.parseDouble(((EditText) findViewById(R.id.input_luminozitate)).getText().toString());
            boolean portabil = ((Switch) findViewById(R.id.switch_portabil)).isChecked();
            ViDeoProiector.TipProiector tip = (ViDeoProiector.TipProiector) spinnerTipProiector.getSelectedItem();

            ViDeoProiector proiector = new ViDeoProiector(marca, rezolutie, luminozitate, portabil, tip);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("proiector", proiector);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}
