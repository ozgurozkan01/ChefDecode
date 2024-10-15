package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainMostLikedRecipe
{
    private String imageSrc;
    private String name;
    private String rate;

    public String getImageSrc() { return imageSrc; }
    public String getName() { return name; }
    public String getRate() { return rate; }

    public void setImageSrc(String src) { imageSrc = src; }
    public void setName(String newName) { name = newName; }
    public void setRate(String newRate) { rate = newRate; }
}
