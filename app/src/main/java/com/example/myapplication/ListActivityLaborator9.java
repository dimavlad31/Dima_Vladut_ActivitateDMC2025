package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivityLaborator9 extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MasinaInfo> masiniList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laborator9);

        listView = findViewById(R.id.listView);

        masiniList = new ArrayList<>();
        masiniList.add(new MasinaInfo(
                "https://cdn.direktcar.ro/blog/uploads/Cele-mai-populare-modele-de-masini-second-hand-pentru-oras.png",
                "BMW XM",
                "https://ro.wikipedia.org/wiki/BMW_XM"
        ));
        masiniList.add(new MasinaInfo(
                "https://www.autovit.ro/blog/wp-content/uploads/2020/12/cele-mai-rapide-masini-din-lume_1392081296-672x442.jpg",
                "Bugatti Veyron",
                "https://ro.wikipedia.org/wiki/Bugatti_Veyron"
        ));
        masiniList.add(new MasinaInfo(
                "https://storage0.dms.mpinteractiv.ro/media/1/1481/21327/18127722/2/37-masina2.jpg",
                "Rolls Royce Cullinan",
                "https://ro.wikipedia.org/wiki/Rolls-Royce_Motor_Cars"
        ));
        masiniList.add(new MasinaInfo(
                "https://www.automobileromanesti.ro/images/mari/Dacia/dacia_1310-2.jpg",
                "Dacia 1310",
                "https://ro.wikipedia.org/wiki/Dacia_1310"
        ));
        masiniList.add(new MasinaInfo(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAiFe2uMAs6HfcY_Q5JaUko8k8LBc8K-Z1Iw&s",
                "Dacia Duster",
                "https://ro.wikipedia.org/wiki/Dacia_Duster"
        ));

        CustomAdapterLaborator9 adapter = new CustomAdapterLaborator9(this, masiniList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListActivityLaborator9.this, DetailActivityLaborator9.class);
            intent.putExtra("webUrl", masiniList.get(position).getWebUrl());
            startActivity(intent);
        });
    }
}
