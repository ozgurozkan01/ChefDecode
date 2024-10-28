package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RecipeDetailController
{
    @FXML
    private Button editButton;
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

    private Database db = new Database();

    boolean isSaved;
    Recipe recipe;

    void setRecipeDetails(Recipe recipe)
    {
        this.recipe = recipe;

        System.out.println("Recipe ID : " + recipe.getID());

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
        preparationTimeLabel.setText(Integer.toString(recipe.getPreparationTime()) + " dk");
        unitPriceLabel.setText(recipe.getTotalUnitPrice() + " ₺");
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
        try {
            Stage stage = (Stage) editButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editPage.fxml"));
            Parent root = loader.load();

            EditController editController = loader.getController();
            editController.setRecipe(recipe);
            editController.setPreviousScene(stage.getScene());
            editController.setBeforeStage(stage);
            editController.loadProperties();

            stage.setScene(new Scene(root));
            stage.setTitle("Edit Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
