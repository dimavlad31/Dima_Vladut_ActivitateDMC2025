package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ViDeoProiector implements Parcelable {
    public enum TipProiector { LED, LASER, DLP, LCD }

    private String marca;
    private int rezolutie;
    private double luminozitate;
    private boolean portabil;
    private TipProiector tip;

    public ViDeoProiector(String marca, int rezolutie, double luminozitate, boolean portabil, TipProiector tip) {
        this.marca = marca;
        this.rezolutie = rezolutie;
        this.luminozitate = luminozitate;
        this.portabil = portabil;
        this.tip = tip;
    }

    protected ViDeoProiector(Parcel in) {
        marca = in.readString();
        rezolutie = in.readInt();
        luminozitate = in.readDouble();
        portabil = in.readByte() != 0;
        tip = TipProiector.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeInt(rezolutie);
        dest.writeDouble(luminozitate);
        dest.writeByte((byte) (portabil ? 1 : 0));
        dest.writeString(tip.name());
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
}
