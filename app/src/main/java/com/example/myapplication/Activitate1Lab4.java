package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Activitate1Lab4 extends AppCompatActivity {


    private static final int REQUEST_ADD_PROIECTOR = 1;
    private static final int REQUEST_EDIT_PROIECTOR = 2;

    private ListView listViewProiectoare;
    private ArrayList<ViDeoProiector> listaProiectoare;
    private ArrayAdapter<ViDeoProiector> adapter;

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

        Button buttonSettings = findViewById(R.id.button_settings);
        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        listViewProiectoare = findViewById(R.id.listView_proiectoare);
        listaProiectoare = new ArrayList<>();

        adapter = new ProiectorAdapter(this, listaProiectoare);
        listViewProiectoare.setAdapter(adapter);

        listViewProiectoare.setOnItemClickListener((parent, view, position, id) -> {
            ViDeoProiector proiector = listaProiectoare.get(position);
            Intent intent = new Intent(this, Activitate2Lab4.class);

            intent.putExtra("proiector_de_editat", proiector);

            intent.putExtra("pozitie", position);

            startActivityForResult(intent, REQUEST_EDIT_PROIECTOR);
        });

        listViewProiectoare.setOnItemLongClickListener((parent, view, position, id) -> {
            ViDeoProiector a = listaProiectoare.get(position);
            String linie = a.toString();
            try {
                FileInputStream fis = openFileInput("favourite_objects.txt");
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);
                fis.close();

                String continutFisier = new String(bytes, StandardCharsets.UTF_8);

                if (continutFisier.contains(linie)) {
                    Toast.makeText(this, "Proiectorul este deja la favorite!", Toast.LENGTH_SHORT).show();
                } else {
                    try (FileOutputStream fos = openFileOutput("favourite_objects.txt", MODE_APPEND)) {
                        fos.write((linie + "\n").getBytes(StandardCharsets.UTF_8));
                        Toast.makeText(this, "Proiector salvat la favorite cu succes!", Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Eroare la accesarea fișierului.", Toast.LENGTH_LONG).show();
            }

            return true;
        });

        Button buttonAddProiector = findViewById(R.id.button4);
        buttonAddProiector.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activitate2Lab4.class);
            startActivityForResult(intent, REQUEST_ADD_PROIECTOR);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            ViDeoProiector proiector = data.getParcelableExtra("proiector");

            switch (requestCode) {

                case REQUEST_ADD_PROIECTOR:
                    listaProiectoare.add(proiector);
                    adapter.notifyDataSetChanged();
                    break;

                case REQUEST_EDIT_PROIECTOR:
                    int poz = data.getIntExtra("pozitie", -1);
                    if (poz != -1) {
                        listaProiectoare.set(poz, proiector);
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    public void ButonApasat1Laborator4(View view){
        Intent intent = new Intent(this, Activitate2Lab4.class);
        startActivityForResult(intent, 1);
    }
}