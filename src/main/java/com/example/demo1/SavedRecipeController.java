package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SavedRecipeController implements Initializable
{
    @FXML
    private Label noSavedRecipeText;

    @FXML
    private Label savedRecipeText;
    @FXML
    private GridPane savedRecipesGrid;

    private static List<Recipe> savedRecipes = new ArrayList<>();

    private static SavedRecipeController instance;
    private static TopBarController topBarController;

    static boolean isSavedRecipePageOpen = false;

    public SavedRecipeController()
    {
    }
    public boolean haveSavedRecipes()
    {
        return savedRecipes.isEmpty();
    }

    public static void addSavedRecipe(Recipe newSavedRecipe)
    {
        if (!savedRecipes.contains(newSavedRecipe))
        {
            savedRecipes.add(newSavedRecipe);
        }
    }

    public static void removeSavedRecipe(Recipe removedRecipe)
    {
        savedRecipes.remove(removedRecipe);
    }

    public static void setInstance(SavedRecipeController controller) { instance = controller; }
    public static SavedRecipeController getInstance()  { return instance; }
    public static void setTopBarController(TopBarController controller) { topBarController = controller; }
    public static TopBarController getTopBarController()  { return topBarController; }

    public static List<Recipe> getSavedRecipes() { return savedRecipes; }

    void loadSavedRecipes()
    {
        if (savedRecipesGrid == null || noSavedRecipeText == null)
        {
            return;
        }

        int recipeColumn = 0;
        int recipeRow = 2;

        try
        {
            if (!haveSavedRecipes())
            {
                savedRecipeText.setVisible(true);
                noSavedRecipeText.setVisible(false);

                for (var recipe : savedRecipes)
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    RecipeController recipeController = fxmlLoader.getController();
                    recipeController.setData(recipe);

                    if (recipeColumn == 5)
                    {
                        recipeColumn = 0;
                        recipeRow++;
                    }

                    savedRecipesGrid.add(anchorPane, recipeColumn++, recipeRow);
                    GridPane.setMargin(anchorPane, new Insets(50, 15, 30, 25));
                }
                return;
            }

            noSavedRecipeText.setVisible(true);
            savedRecipeText.setVisible(false);
        }

        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        instance = this;

        noSavedRecipeText.setText("No Saved Recipe");
        noSavedRecipeText.setVisible(false);
        savedRecipeText.setText("Recipes You Saved");
        savedRecipeText.setVisible(true);

        loadSavedRecipes();
    }
}
