package com.example.myapplication;

public class MasinaInfo {
    private String imageUrl;
    private String title;
    private String webUrl;

    public MasinaInfo(String imageUrl, String title, String webUrl) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.webUrl = webUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getWebUrl() {
        return webUrl;
    }
}