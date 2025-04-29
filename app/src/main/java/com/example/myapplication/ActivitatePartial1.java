package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitatePartial1 extends AppCompatActivity {



    private ListView listViewHuse;

    private ArrayList<HusaTelefon> listaHuse;
    private ArrayAdapter<HusaTelefon> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_partial1);

        listViewHuse = findViewById(R.id.dima_vlad_listView);
        listaHuse = new ArrayList<>();

        //adapter = new ProiectorAdapter(this, listaHuse);
        listViewHuse.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void ButonApasat1Partial(View view){
        Intent intent = new Intent(this, ActivitatePartial2.class);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        HusaTelefon husa = data.getParcelableExtra("husaTelefon");
    }
}