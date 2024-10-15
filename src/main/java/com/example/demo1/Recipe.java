package com.example.demo1;

public class Recipe
{
    private String name;
    private String rate;
    private String imgSrc;
    private String starImgSrc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setStarImgSrc(String starImgSrc) {
        this.starImgSrc = starImgSrc;
    }
    public String getStarImgSrc() {
        return starImgSrc;
    }
}
