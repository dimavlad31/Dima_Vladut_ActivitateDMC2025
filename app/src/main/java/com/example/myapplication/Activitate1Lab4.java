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
            listaProiectoare.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Proiector șters", Toast.LENGTH_SHORT).show();
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