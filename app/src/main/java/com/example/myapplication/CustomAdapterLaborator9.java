package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomAdapterLaborator9 extends BaseAdapter {

    private Context context;
    private List<MasinaInfo> masiniList;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public CustomAdapterLaborator9(Context context, List<MasinaInfo> masiniList) {
        this.context = context;
        this.masiniList = masiniList;
    }

    @Override
    public int getCount() {
        return masiniList.size();
    }

    @Override
    public Object getItem(int position) {
        return masiniList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_item_laborator9, parent, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);

        MasinaInfo masina = masiniList.get(position);

        textView.setText(masina.getTitle());

        executorService.execute(() -> {
            try {
                InputStream inputStream = new URL(masina.getImageUrl()).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                mainHandler.post(() -> imageView.setImageBitmap(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return view;
    }
}
