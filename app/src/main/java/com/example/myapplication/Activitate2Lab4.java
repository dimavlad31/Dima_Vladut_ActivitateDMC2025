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

        ArrayAdapter<ViDeoproiector.TipProiector> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ViDeoproiector.TipProiector.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerTipProiector=findViewById(R.id.spinner_tip_proiector);
        spinnerTipProiector.setAdapter(adapter);

        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(v -> {
            EditText inputMarca = findViewById(R.id.input_marca);
            EditText inputRezolutie = findViewById(R.id.input_rezolutie);
            EditText inputLuminozitate = findViewById(R.id.input_luminozitate);
            Switch switchPortabil = findViewById(R.id.switch_portabil);
            Spinner spinnerTipProiector2 = findViewById(R.id.spinner_tip_proiector);

            String marca = inputMarca.getText().toString();
            int rezolutie = Integer.parseInt(inputRezolutie.getText().toString());
            double luminozitate = Double.parseDouble(inputLuminozitate.getText().toString());
            boolean portabil = switchPortabil.isChecked();
            ViDeoproiector.TipProiector tip = (ViDeoproiector.TipProiector) spinnerTipProiector2.getSelectedItem();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("marca", marca);
            returnIntent.putExtra("rezolutie", rezolutie);
            returnIntent.putExtra("luminozitate", luminozitate);
            returnIntent.putExtra("portabil", portabil);
            returnIntent.putExtra("tip", tip);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}