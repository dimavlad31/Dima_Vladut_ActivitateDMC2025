package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class ViDeoproiector {
    public enum TipProiector { LED, LASER, DLP, LCD }

    private String marca;
    private int rezolutie;
    private double luminozitate;
    private boolean portabil;
    private TipProiector tip;

    public ViDeoproiector(String marca, int rezolutie, double luminozitate, boolean portabil, TipProiector tip) {
        this.marca = marca;
        this.rezolutie = rezolutie;
        this.luminozitate = luminozitate;
        this.portabil = portabil;
        this.tip = tip;
    }

    public String getMarca() {
        return marca;
    }

    public int getRezolutie() {
        return rezolutie;
    }

    public double getLuminozitate() {
        return luminozitate;
    }

    public boolean isPortabil() {
        return portabil;
    }

    public TipProiector getTip() {
        return tip;
    }
}

public class Activitate1Lab4 extends AppCompatActivity {

    private EditText inputMarca, inputRezolutie, inputLuminozitate;
    private Switch switchPortabil;
    private Spinner spinnerTipProiector;
    private TextView textMarca, textRezolutie, textLuminozitate, textPortabil, textTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate1_lab4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inițializare view-uri pentru afișarea datelor
        textMarca = findViewById(R.id.text_marca);
        textRezolutie = findViewById(R.id.text_rezolutie);
        textLuminozitate = findViewById(R.id.text_luminozitate);
        textPortabil = findViewById(R.id.text_portabil);
        textTip = findViewById(R.id.text_tip);
        // Preluare date primite de la Activitate2Lab4
        Intent intent = getIntent();
        if (intent.hasExtra("marca")) {
            String marca = intent.getStringExtra("marca");
            int rezolutie = intent.getIntExtra("rezolutie", 0);
            double luminozitate = intent.getDoubleExtra("luminozitate", 0.0);
            boolean portabil = intent.getBooleanExtra("portabil", false);
            ViDeoproiector.TipProiector tip = (ViDeoproiector.TipProiector) intent.getSerializableExtra("tip");

            ViDeoproiector proiector = new ViDeoproiector(marca, rezolutie, luminozitate, portabil, tip);
        }
    }

    public void ButonApasat1Laborator4(View view){
        Intent intent = new Intent(this, Activitate2Lab4.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String marca = data.getStringExtra("marca");
            int rezolutie = data.getIntExtra("rezolutie", 0);
            double luminozitate = data.getDoubleExtra("luminozitate", 0.0);
            boolean portabil = data.getBooleanExtra("portabil", false);
            ViDeoproiector.TipProiector tip = (ViDeoproiector.TipProiector) data.getSerializableExtra("tip");

            // Creăm obiectul ViDeoproiector
            ViDeoproiector proiector = new ViDeoproiector(marca, rezolutie, luminozitate, portabil, tip);

            // Afișăm datele în interfață
            textMarca.setText("Marca: " + proiector.getMarca());
            textRezolutie.setText("Rezoluție: " + proiector.getRezolutie() + "p");
            textLuminozitate.setText("Luminozitate: " + proiector.getLuminozitate() + " lumeni");
            textPortabil.setText("Portabil: " + (proiector.isPortabil() ? "Da" : "Nu"));
            textTip.setText("Tip: " + proiector.getTip().name());
        }
    }
}