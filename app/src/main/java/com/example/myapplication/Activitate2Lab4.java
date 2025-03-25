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
    private EditText etMarca, etRezolutie, etLuminozitate;
    private Switch switchPortabil;
    private Spinner spinnerTipProiector;

    private ViDeoProiector proiectorDeEditat = null;

    private int pozitie = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate2_lab4);

        etMarca = findViewById(R.id.input_marca);
        etRezolutie = findViewById(R.id.input_rezolutie);
        etLuminozitate = findViewById(R.id.input_luminozitate);
        switchPortabil = findViewById(R.id.switch_portabil);
        spinnerTipProiector = findViewById(R.id.spinner_tip_proiector);
        datePickerProduction = findViewById(R.id.date_picker_production);

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

        Intent returnIntent = new Intent();
        returnIntent.putExtra("proiector", rezultat);
        returnIntent.putExtra("pozitie", pozitie);

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}