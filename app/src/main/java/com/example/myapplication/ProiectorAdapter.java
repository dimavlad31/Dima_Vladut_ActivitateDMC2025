package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ProiectorAdapter extends ArrayAdapter<ViDeoProiector> {

    private final Context context;
    private final List<ViDeoProiector> proiectoare;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public ProiectorAdapter(@NonNull Context context, @NonNull List<ViDeoProiector> obiecte) {
        super(context, 0, obiecte);
        this.context = context;
        this.proiectoare = obiecte;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

         if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.row_proiector, parent, false);
        }

        ViDeoProiector proiectorCurent = proiectoare.get(position);

        TextView tvBrand          = convertView.findViewById(R.id.textView_brand);
        TextView tvRezolutie      = convertView.findViewById(R.id.textView_rezolutie);
        TextView tvLuminozitate   = convertView.findViewById(R.id.textView_luminozitate);
        TextView tvPortabil       = convertView.findViewById(R.id.textView_portabil);
        TextView tvTip            = convertView.findViewById(R.id.textView_tip);
        TextView tvDataProductie  = convertView.findViewById(R.id.textView_dataProductie);

        tvBrand.setText("Marca: " + proiectorCurent.getMarca());
        tvRezolutie.setText("Rezoluție: " + proiectorCurent.getRezolutie() + "p");
        tvLuminozitate.setText("Luminozitate: " + proiectorCurent.getLuminozitate() + " lumeni");
        tvPortabil.setText("Portabil: " + (proiectorCurent.isPortabil() ? "Da" : "Nu"));
        tvTip.setText("Tip: " + proiectorCurent.getTip().name());
        tvDataProductie.setText("Data Producției: " + dateFormat.format(proiectorCurent.getDataProductiei()));

        return convertView;
    }
}