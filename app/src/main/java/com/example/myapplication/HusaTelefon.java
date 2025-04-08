package com.example.myapplication;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class HusaTelefon implements Parcelable{
    public enum marcaHusa { Apple, MobileFox, SmartCase, LCD }
    private String Material;
    private Double lungime;
    private marcaHusa marca;//pentru spinner
    private boolean wirelessCharging;//pentru checkbox

    public marcaHusa getMarca() {
        return marca;
    }

    public void setMarca(marcaHusa marca) {
        this.marca = marca;
    }

    protected HusaTelefon(Parcel in) {
        Material = in.readString();
        lungime = in.readDouble();
        wirelessCharging = in.readByte() != 0;
        marca = HusaTelefon.marcaHusa.valueOf(in.readString());

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Material);
        dest.writeDouble(lungime);
        dest.writeByte((byte) (wirelessCharging ? 1 : 0));
        dest.writeString(marca.name());
    }
    public HusaTelefon(String material, Double lungime, marcaHusa marca, boolean wirelessCharging) {
        Material = material;
        this.lungime = lungime;
        this.marca = marca;
        this.wirelessCharging = wirelessCharging;
    }

    @Override
    public String toString() {
        return "HusaTelefon{" +
                "Material='" + Material + '\'' +
                ", lungime=" + lungime +
                ", marca=" + marca +
                ", wirelessCharging=" + wirelessCharging +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }



    public static final Parcelable.Creator<HusaTelefon> CREATOR = new Parcelable.Creator<HusaTelefon>() {
        @Override
        public HusaTelefon createFromParcel(Parcel in) {
            return new HusaTelefon(in);
        }

        @Override
        public HusaTelefon[] newArray(int size) {
            return new HusaTelefon[size];
        }
    };


    public boolean isWirelessCharging() {
        return wirelessCharging;
    }



    public void setWirelessCharging(boolean wirelessCharging) {
        this.wirelessCharging = wirelessCharging;
    }

    public String getMaterial() {
        return Material;
    }

    public Double getLungime() {
        return lungime;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public void setLungime(Double lungime) {
        this.lungime = lungime;
    }


}


