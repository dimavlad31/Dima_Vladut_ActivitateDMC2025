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

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProiectoare);
        listViewProiectoare.setAdapter(adapter);

        listViewProiectoare.setOnItemClickListener((parent, view, position, id) -> {
            ViDeoProiector proiector = listaProiectoare.get(position);
            Toast.makeText(this, proiector.toString(), Toast.LENGTH_LONG).show();
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
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ViDeoProiector proiector = data.getParcelableExtra("proiector");
            listaProiectoare.add(proiector);
            adapter.notifyDataSetChanged();
        }
    }

    public void ButonApasat1Laborator4(View view){
        Intent intent = new Intent(this, Activitate2Lab4.class);
        startActivityForResult(intent, 1);
    }
}