package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViDeoProiector implements Parcelable {
    public enum TipProiector { LED, LASER, DLP, LCD }

    private String marca;
    private int rezolutie;
    private double luminozitate;
    private boolean portabil;
    private TipProiector tip;
    private Date dataProductiei;

    public ViDeoProiector(String marca, int rezolutie, double luminozitate, boolean portabil, TipProiector tip, Date dataProductiei) {
        this.marca = marca;
        this.rezolutie = rezolutie;
        this.luminozitate = luminozitate;
        this.portabil = portabil;
        this.tip = tip;
        this.dataProductiei = dataProductiei;
    }

    protected ViDeoProiector(Parcel in) {
        marca = in.readString();
        rezolutie = in.readInt();
        luminozitate = in.readDouble();
        portabil = in.readByte() != 0;
        tip = TipProiector.valueOf(in.readString());
        dataProductiei = new Date(in.readLong());
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return "Marca: " + marca + ", Rezolutie: " + rezolutie + "p, " +
                "Luminozitate: " + luminozitate + " lumeni, " +
                "Portabil: " + (portabil ? "Da" : "Nu") + ", " +
                "Tip: " + tip.name() + ", " +
                "Data Fabricatie: " + dateFormat.format(dataProductiei);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeInt(rezolutie);
        dest.writeDouble(luminozitate);
        dest.writeByte((byte) (portabil ? 1 : 0));
        dest.writeString(tip.name());
        dest.writeLong(dataProductiei.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ViDeoProiector> CREATOR = new Creator<ViDeoProiector>() {
        @Override
        public ViDeoProiector createFromParcel(Parcel in) {
            return new ViDeoProiector(in);
        }

        @Override
        public ViDeoProiector[] newArray(int size) {
            return new ViDeoProiector[size];
        }
    };

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

    public Date getDataProductiei() {
        return dataProductiei;
    }
}