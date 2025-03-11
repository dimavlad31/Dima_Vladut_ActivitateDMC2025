package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activitate1Lab4 extends AppCompatActivity {

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
        Clasa a;
        textMarca = findViewById(R.id.text_marca);
        textRezolutie = findViewById(R.id.text_rezolutie);
        textLuminozitate = findViewById(R.id.text_luminozitate);
        textPortabil = findViewById(R.id.text_portabil);
        textTip = findViewById(R.id.text_tip);

        Intent intent = getIntent();
        if (intent.hasExtra("proiector")) {
            ViDeoProiector viDeoproiector = intent.getParcelableExtra("proiector");

            textMarca.setText("Marca: " + viDeoproiector.getMarca());
            textRezolutie.setText("Rezoluție: " + viDeoproiector.getRezolutie() + "p");
            textLuminozitate.setText("Luminozitate: " + viDeoproiector.getLuminozitate() + " lumeni");
            textPortabil.setText("Portabil: " + (viDeoproiector.isPortabil() ? "Da" : "Nu"));
            textTip.setText("Tip: " + viDeoproiector.getTip().name());
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
            ViDeoProiector proiector = (ViDeoProiector) data.getParcelableExtra("proiector");

            textMarca.setText("Marca: " + proiector.getMarca());
            textRezolutie.setText("Rezoluție: " + proiector.getRezolutie() + "p");
            textLuminozitate.setText("Luminozitate: " + proiector.getLuminozitate() + " lumeni");
            textPortabil.setText("Portabil: " + (proiector.isPortabil() ? "Da" : "Nu"));
            textTip.setText("Tip: " + proiector.getTip().name());
        }
    }
}