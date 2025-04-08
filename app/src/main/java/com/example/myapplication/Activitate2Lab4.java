package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Activitate2Lab4 extends AppCompatActivity {

    private DatePicker datePickerProduction;
    private EditText etMarca, etRezolutie, etLuminozitate;
    private Switch switchPortabil;
    private Spinner spinnerTipProiector;

    private ViDeoProiector proiectorDeEditat = null;

    private int pozitie = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate2_lab4);
        SharedPreferences preferences = getSharedPreferences("UserSettings", MODE_PRIVATE);
        float textSize = preferences.getFloat("textSize", 16f);
        String textColor = preferences.getString("textColor", "#000000");


        etMarca = findViewById(R.id.input_marca);
        etRezolutie = findViewById(R.id.input_rezolutie);
        etLuminozitate = findViewById(R.id.input_luminozitate);
        switchPortabil = findViewById(R.id.switch_portabil);
        spinnerTipProiector = findViewById(R.id.spinner_tip_proiector);
        datePickerProduction = findViewById(R.id.date_picker_production);

        etMarca.setTextSize(textSize);
        etRezolutie.setTextSize(textSize);
        etLuminozitate.setTextSize(textSize);

        etMarca.setTextColor(Color.parseColor(textColor));
        etRezolutie.setTextColor(Color.parseColor(textColor));
        etLuminozitate.setTextColor(Color.parseColor(textColor));


        datePickerProduction.setMaxDate(new Date().getTime());

        ArrayAdapter<ViDeoProiector.TipProiector> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ViDeoProiector.TipProiector.values()
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipProiector.setAdapter(adapter);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("proiector_de_editat")) {
            proiectorDeEditat = intent.getParcelableExtra("proiector_de_editat");
            pozitie = intent.getIntExtra("pozitie", -1);

            etMarca.setText(proiectorDeEditat.getMarca());
            etRezolutie.setText(String.valueOf(proiectorDeEditat.getRezolutie()));
            etLuminozitate.setText(String.valueOf(proiectorDeEditat.getLuminozitate()));
            switchPortabil.setChecked(proiectorDeEditat.isPortabil());

            ViDeoProiector.TipProiector tipActual = proiectorDeEditat.getTip();
            spinnerTipProiector.setSelection(tipActual.ordinal());

            Calendar c = Calendar.getInstance();
            c.setTime(proiectorDeEditat.getDataProductiei());
            datePickerProduction.updateDate(c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
        }

        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(v -> salveazaProiector());
    }

    private void salveazaProiector() {

        String marca = etMarca.getText().toString().trim();
        int rezolutie = Integer.parseInt(etRezolutie.getText().toString().trim());
        double luminozitate = Double.parseDouble(etLuminozitate.getText().toString().trim());
        boolean portabil = switchPortabil.isChecked();

        ViDeoProiector.TipProiector tip =
                (ViDeoProiector.TipProiector) spinnerTipProiector.getSelectedItem();

        int day = datePickerProduction.getDayOfMonth();
        int month = datePickerProduction.getMonth();
        int year = datePickerProduction.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date dataProductiei = calendar.getTime();


        ViDeoProiector rezultat = new ViDeoProiector(
                marca,
                rezolutie,
                luminozitate,
                portabil,
                tip,
                dataProductiei
        );
        String linie = rezultat.toString();

        try {
            FileInputStream fis = openFileInput("objects.txt");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();

            String continutFisier = new String(bytes, StandardCharsets.UTF_8);

            if (continutFisier.contains(linie)) {
                Toast.makeText(this, "Proiectorul este deja aflat in fisierul obiecte!", Toast.LENGTH_SHORT).show();
            } else {
                try (FileOutputStream fos = openFileOutput("objects.txt", MODE_APPEND)) {
                    fos.write((linie + "\n").getBytes(StandardCharsets.UTF_8));
                    Toast.makeText(this, "Proiector salvat cu succes!", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Eroare la accesarea fișierului.", Toast.LENGTH_LONG).show();
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("proiector", rezultat);
        returnIntent.putExtra("pozitie", pozitie);

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}