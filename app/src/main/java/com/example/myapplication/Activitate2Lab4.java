package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Calendar;
import java.util.Date;

public class Activitate2Lab4 extends AppCompatActivity {

    private DatePicker datePickerProduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate2_lab4);

        ArrayAdapter<ViDeoProiector.TipProiector> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ViDeoProiector.TipProiector.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerTipProiector = findViewById(R.id.spinner_tip_proiector);
        spinnerTipProiector.setAdapter(adapter);

        datePickerProduction = findViewById(R.id.date_picker_production);
        Date date = new Date();
        datePickerProduction.setMaxDate(date.getTime());


        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(v -> {
            String marca = ((EditText) findViewById(R.id.input_marca)).getText().toString();
            int rezolutie = Integer.parseInt(((EditText) findViewById(R.id.input_rezolutie)).getText().toString());
            double luminozitate = Double.parseDouble(((EditText) findViewById(R.id.input_luminozitate)).getText().toString());
            boolean portabil = ((Switch) findViewById(R.id.switch_portabil)).isChecked();
            ViDeoProiector.TipProiector tip = (ViDeoProiector.TipProiector) spinnerTipProiector.getSelectedItem();

            int day = datePickerProduction.getDayOfMonth();
            int month = datePickerProduction.getMonth();
            int year = datePickerProduction.getYear();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            Date dataProductiei = calendar.getTime();



            ViDeoProiector proiector = new ViDeoProiector(marca, rezolutie, luminozitate, portabil, tip, dataProductiei);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("proiector", proiector);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}