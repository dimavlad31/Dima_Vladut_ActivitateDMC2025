package com.example.myapplication;

public class Masina {
    private String culoare;
    private String marca;
    private int cp;

    public Masina(String culoare, String marca, int cp) {
        this.culoare = culoare;
        this.marca = marca;
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "culoare='" + culoare + '\'' +
                ", marca='" + marca + '\'' +
                ", cp=" + cp +
                '}';
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getMarca() {
        return marca;
    }

    public int getCp() {
        return cp;
    }

    public Masina() {
    }
}
