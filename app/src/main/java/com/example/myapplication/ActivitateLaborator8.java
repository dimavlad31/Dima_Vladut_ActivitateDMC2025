package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class ActivitateLaborator8 extends AppCompatActivity {

    private EditText etMarca, etCuloare, etCaiPutere,etInputCuloare;
    private Button btnIntroduInBazaDeDate,btnDeleteCai, btnVizualizeazaMasini,btnVizualizeazaMasiniCuloare;
    private ListView lvMasini,lvMasiniCuloare;
    private SQLiteDatabase db;
    private EditText etPrimaLitera;
    private Button btnCresteCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_laborator8);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = openOrCreateDatabase("MasinaDB.db", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS masina (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "marca TEXT, " +
                "culoare TEXT, " +
                "cp INTEGER);"
        );

        etMarca = findViewById(R.id.etMarca);
        etCuloare = findViewById(R.id.etCuloare);
        etCaiPutere = findViewById(R.id.etCaiPutere);
        etInputCuloare = findViewById(R.id.etSelectie_Culoare);
        btnIntroduInBazaDeDate = findViewById(R.id.btnIntroduInBazaDeDate);
        btnVizualizeazaMasini = findViewById(R.id.btnVizualizeazaMasini);
        btnVizualizeazaMasiniCuloare = findViewById(R.id.btnVizualizeazaMasiniCuloare);
        btnDeleteCai = findViewById(R.id.btnDeleteCai);
        btnCresteCP = findViewById(R.id.btnCresteCP);
        lvMasini = findViewById(R.id.lvMasini);
        etPrimaLitera = findViewById(R.id.etPrimaLitera);

        final EditText etCaiPutereSelect_1 = findViewById(R.id.etCaiPutereSelect_1);
        final EditText etCaiPutereSelect_2 = findViewById(R.id.etCaiPutereSelect_2);

        Button btnVizualizeazaCaiPutere = findViewById(R.id.btnVizualizeazaCaiPutere);

        btnCresteCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String litera = etPrimaLitera.getText().toString().trim();
                if (litera.isEmpty()) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Introduceți o literă!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                String query = "UPDATE masina SET cp = cp + 1 WHERE marca LIKE ?";
                db.execSQL(query, new Object[]{ litera + "%" });

                Toast.makeText(ActivitateLaborator8.this,
                        "Cai putere crescute cu 1 pentru marca ce începe cu: " + litera,
                        Toast.LENGTH_LONG).show();

                actualizeazaListaMasini();
            }
        });

        btnIntroduInBazaDeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marca = etMarca.getText().toString().trim();
                String culoare = etCuloare.getText().toString().trim();
                String cpString = etCaiPutere.getText().toString().trim();

                if (marca.isEmpty() || culoare.isEmpty() || cpString.isEmpty()) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Completați toate câmpurile!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int caiPutere;
                try {
                    caiPutere = Integer.parseInt(cpString);
                } catch (NumberFormatException e) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Introduceți un număr valid pentru cai putere!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues values = new ContentValues();
                values.put("marca", marca);
                values.put("culoare", culoare);
                values.put("cp", caiPutere);

                long result = db.insert("masina", null, values);
                if (result != -1) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Mașină inserată cu succes!", Toast.LENGTH_SHORT).show();

                    etMarca.setText("");
                    etCuloare.setText("");
                    etCaiPutere.setText("");
                } else {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Eroare la inserare!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteCai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etCaiPutereSelect_3 = findViewById(R.id.etCaiPutereSelect_3);
                String cpString = etCaiPutereSelect_3.getText().toString().trim();


                if (cpString.isEmpty()) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Introduceți o valoare de cai putere!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int cpThreshold;
                try {
                    cpThreshold = Integer.parseInt(cpString);
                } catch (NumberFormatException e) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Introduceți un număr valid!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                int rowsDeleted = db.delete("masina", "cp < ?",
                        new String[]{ String.valueOf(cpThreshold) });


                if (rowsDeleted > 0) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Șterse " + rowsDeleted + " mașini cu CP mai mic de " + cpThreshold,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Nu s-au găsit mașini cu CP mai mic de " + cpThreshold,
                            Toast.LENGTH_LONG).show();
                }


                actualizeazaListaMasini();
            }
        });

        btnVizualizeazaCaiPutere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String minCPString = etCaiPutereSelect_1.getText().toString().trim();
                String maxCPString = etCaiPutereSelect_2.getText().toString().trim();

                if (minCPString.isEmpty() || maxCPString.isEmpty()) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Introduceți ambele valori pentru interval!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int minCP;
                int maxCP;
                try {
                    minCP = Integer.parseInt(minCPString);
                    maxCP = Integer.parseInt(maxCPString);
                } catch (NumberFormatException e) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Introduceți valori numerice valide!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (minCP > maxCP) {
                    int temp = minCP;
                    minCP = maxCP;
                    maxCP = temp;
                }

               Cursor cursor = db.rawQuery(
                        "SELECT * FROM masina WHERE cp BETWEEN ? AND ?",
                        new String[] { String.valueOf(minCP), String.valueOf(maxCP) }
                );

                List<String> listaMasini = new ArrayList<>();

                if (cursor.moveToFirst()) {
                    int colIdMarca = cursor.getColumnIndex("marca");
                    int colIdCuloare = cursor.getColumnIndex("culoare");
                    int colIdCP = cursor.getColumnIndex("cp");

                    do {
                        String marca = cursor.getString(colIdMarca);
                        String culoare = cursor.getString(colIdCuloare);
                        int cp = cursor.getInt(colIdCP);

                        String descriereMasina = "Marca: " + marca
                                + " | Culoare: " + culoare
                                + " | CP: " + cp;

                        listaMasini.add(descriereMasina);
                    } while (cursor.moveToNext());
                }
                cursor.close();

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        ActivitateLaborator8.this,
                        android.R.layout.simple_list_item_1,
                        listaMasini
                );
                lvMasini.setAdapter(adapter);

                if (listaMasini.isEmpty()) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Nu există mașini între " + minCP + " și " + maxCP + " cai putere.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnVizualizeazaMasini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = db.rawQuery("SELECT * FROM masina", null);


                List<String> listaMasini = new ArrayList<>();

                if (cursor.moveToFirst()) {
                    int colIdMarca = cursor.getColumnIndex("marca");
                    int colIdCuloare = cursor.getColumnIndex("culoare");
                    int colIdCP = cursor.getColumnIndex("cp");

                    do {
                        String marca = cursor.getString(colIdMarca);
                        String culoare = cursor.getString(colIdCuloare);
                        int cp = cursor.getInt(colIdCP);

                        String descriereMasina = "Marca: " + marca
                                + " | Culoare: " + culoare
                                + " | CP: " + cp;

                        listaMasini.add(descriereMasina);
                    } while (cursor.moveToNext());
                }
                cursor.close();  // închidem cursorul

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        ActivitateLaborator8.this,
                        android.R.layout.simple_list_item_1,
                        listaMasini
                );
                lvMasini.setAdapter(adapter);
            }
        });

        btnVizualizeazaMasiniCuloare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String culoareCautata = etInputCuloare.getText().toString().trim();
                if (culoareCautata.isEmpty()) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Introduceți o culoare!", Toast.LENGTH_SHORT).show();
                    return;
                }


                Cursor cursor = db.rawQuery(
                        "SELECT * FROM masina WHERE culoare = ?",
                        new String[]{culoareCautata}
                );


                List<String> listaMasini = new ArrayList<>();

                if (cursor.moveToFirst()) {
                    int colIdMarca = cursor.getColumnIndex("marca");
                    int colIdCuloare = cursor.getColumnIndex("culoare");
                    int colIdCP = cursor.getColumnIndex("cp");

                    do {
                        String marca = cursor.getString(colIdMarca);
                        String culoare = cursor.getString(colIdCuloare);
                        int cp = cursor.getInt(colIdCP);

                        String descriereMasina = "Marca: " + marca
                                + " | Culoare: " + culoare
                                + " | CP: " + cp;

                        listaMasini.add(descriereMasina);
                    } while (cursor.moveToNext());
                }
                cursor.close();

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        ActivitateLaborator8.this,
                        android.R.layout.simple_list_item_1,
                        listaMasini
                );
                lvMasini.setAdapter(adapter);

                if (listaMasini.isEmpty()) {
                    Toast.makeText(ActivitateLaborator8.this,
                            "Nu există mașini cu culoarea: " + culoareCautata,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (db != null && db.isOpen()) {
            db.close();
        }
        super.onDestroy();
    }

    private void actualizeazaListaMasini() {
        Cursor cursor = db.rawQuery("SELECT * FROM masina", null);
        List<String> listaMasini = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int colMarca = cursor.getColumnIndex("marca");
            int colCuloare = cursor.getColumnIndex("culoare");
            int colCP = cursor.getColumnIndex("cp");

            do {
                String marca = cursor.getString(colMarca);
                String culoare = cursor.getString(colCuloare);
                int cp = cursor.getInt(colCP);

                String descriereMasina = "Marca: " + marca
                        + " | Culoare: " + culoare
                        + " | CP: " + cp;

                listaMasini.add(descriereMasina);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ActivitateLaborator8.this,
                android.R.layout.simple_list_item_1,
                listaMasini
        );
        lvMasini.setAdapter(adapter);
    }
}

