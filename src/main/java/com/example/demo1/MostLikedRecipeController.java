package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MostLikedRecipeController
{
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label rate;

    private Recipe mainMostLikedRecipe;

    //private List<MainMostLikedRecipe> mainMostLikedRecipes = new ArrayList<>();
    //private MainMostLikedRecipe mainMostLikedRecipe;

    public void setData(Recipe mostLikedRecipe)
    {
        this.mainMostLikedRecipe = mostLikedRecipe;

        rate.setText(String.valueOf(mostLikedRecipe.getRate()));
        name.setText(mainMostLikedRecipe.getName());
        //Image mostLikedImage = new Image(getClass().getResourceAsStream(mainMostLikedRecipe.getImageSrc()));
        //image.setImage(mostLikedImage);
    }
}
