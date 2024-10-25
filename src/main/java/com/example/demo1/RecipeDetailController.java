package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

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
    @FXML
    private Label preparationTimeLabel;
    @FXML
    private Label unitPriceLabel;

    Database db = new Database();

    boolean isSaved;
    Recipe recipe;

    void setRecipeDetails(Recipe recipe)
    {
        this.recipe = recipe;

/*
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
*/

        List<String> ingredientNameList = db.getIngredientsByRecipeId(recipe.getID());

        if (ingredientNameList.isEmpty())
        {
            System.out.println("List is empty.");
        }
        for (var ingredient : ingredientNameList)
        {
            ingredients.getChildren().add(new Label(ingredient));
        }

        instructions.getChildren().add(new Label(recipe.getInstruction()));
        rate.setText(Float.toString(recipe.getRate()));
        ratedInfo.setText("( " + recipe.getNumberPoints() + " people rated )");
        recipeName.setText(recipe.getName());
        preparationTimeLabel.setText(Integer.toString(recipe.getPreparationTime()));
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
