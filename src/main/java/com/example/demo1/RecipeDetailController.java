package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RecipeDetailController
{
    @FXML
    private VBox ingredients;
    @FXML
    private VBox instructions;
    @FXML
    private Label rate;
    @FXML
    private Label ratedInfo;
    @FXML
    private ImageView recipeImage;
    @FXML
    private Label recipeName;
    @FXML
    private Button saveButton;

    boolean isSaved;
    Recipe recipe;

    void setRecipeDetails(Recipe recipe)
    {
        this.recipe = recipe;

        instructions.getChildren().addAll(new Label("1-\u00A0 Bulgur (1 paket)"), new Label("2-\u00A0 Salça (2 yemek kaşığı) "), new Label("3-\u00A0İyot (2 çay kaşığı)"));
        Label l = new Label();
        l.setText("1-\u00A0Kýyma, tuz, karabiber, kimyon ve diðer baharatlarý bir kapta iyice yoðurun (5 dakika).");
        l.setWrapText(true);
        l.setStyle("-fx-font-size: 15px;");

        Label l2 = new Label();
        l2.setText("2-\u00A0Kýzartma tavasýnda 1-2 yemek kaþýðý yað ýsýtýn ve patlýcanlarý her iki tarafýný altýn rengi alana kadar kýzartýn (10 dakika).");
        l2.setWrapText(true);
        l2.setStyle("-fx-font-size: 15px;");

        Label l3 = new Label();
        l3.setText("3-\u00A0Karýþýmý yaðlanmýþ fýrýn kabýna dökün ve önceden ýsýtýlmýþ 180°C fýrýnda yaklaþýk 25-30 dakika piþirin (30 dakika).");
        l3.setWrapText(true);
        l3.setStyle("-fx-font-size: 15px;");

        ingredients.getChildren().addAll(l, l2, l3);
        rate.setText("3.0");
        ratedInfo.setText("( 1522342 people rated )");
        recipeName.setText("Baklava");

    }

    public void updateSaveButton(boolean b)
    {
        isSaved = b;
        String saveText = isSaved ? "Unsave" : "Save";
        saveButton.setText(saveText);
    }

    @FXML
    public void OnSavedButtonPressed()
    {
        if (recipe.isSaved())  { SavedRecipeController.getSavedRecipes().remove(recipe); }
        else                   { SavedRecipeController.addSavedRecipe(recipe); }

        recipe.setIsSaved(!recipe.isSaved());
        updateSaveButton(!isSaved);
    }

    @FXML
    public void OnEditButtonPressed()
    {

    }
}
