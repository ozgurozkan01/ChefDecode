package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private HBox includeHeader;
    @FXML
    private ScrollPane menusScrollPane;
    @FXML
    private ScrollPane mostLikedScrollPane;
    @FXML
    private ScrollPane recipesScrollPane;
    @FXML
    private GridPane recipesGrid;
    @FXML
    private GridPane mostLikeGrid;

    private List<Recipe> recipes = new ArrayList<>();
    private List<Recipe> mainMostLikedRecipes = new ArrayList<>();

    MostLikedRecipeController mostLikedRecipeController;
    SavedRecipeController savedRecipeController;

    public MainController()
    {
        mostLikedRecipeController = new MostLikedRecipeController();
        savedRecipeController = new SavedRecipeController();
    }

    public void OnCategoryButtonPressed()
    {
        System.out.println("Category Button Pressed");
    }

    private Database database = new Database();

    private List<Recipe> getRecipesData() {
        List<Recipe> recipeList = database.getAllRecipes();

        return recipeList;
    }

    void loadRecipes() {
        recipes.addAll(getRecipesData());

        int recipeColumn = 0;
        int recipeRow = 2;

        try
        {
            for (var recipe : recipes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                RecipeController recipeController = fxmlLoader.getController();
                recipeController.setData(recipe);

                if (recipeColumn == 2)
                {
                    recipeColumn = 0;
                    recipeRow++;
                }

                recipesGrid.add(anchorPane, recipeColumn++, recipeRow);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }

        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    void loadMostLikedRecipes() {
        mainMostLikedRecipes.addAll(getRecipesData());

        int recipeColumn = 0;
        int recipeRow = 2;

        try {
            for (var mostLikedRecipe : mainMostLikedRecipes) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/mainMostLikedRecipes.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();
                MostLikedRecipeController mostLikedRecipeController = fxmlLoader.getController();
                mostLikedRecipeController.setData(mostLikedRecipe);

                mostLikeGrid.add(anchorPane, recipeColumn, recipeRow++);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void goToCategory(ActionEvent event) {
        try {
            Parent mainRoot = FXMLLoader.load(getClass().getResource("category-page.fxml"));

            Scene mainScene = new Scene(mainRoot);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainScene);
            window.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadRecipes();
        loadMostLikedRecipes();
        //mostLikedRecipeController.loadMostLikedRecipes(mostLikeGrid);
    }
}