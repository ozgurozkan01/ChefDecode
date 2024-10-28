package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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

    private Button clickedButton;

    private static String buttonID;

    private Database database = new Database();
    private StockManager stockManager = new StockManager();

    public static List<Recipe> recipes = new ArrayList<>();
    private static List<Recipe> mainMostLikedRecipes = new ArrayList<>();

    public void setButtonID(String buttonID) {
        this.buttonID = buttonID;
    }

    static public String getButtonID() {
        return buttonID;
    }


    private List<Recipe> getRecipesData()
    {
        List<Recipe> recipeList = database.getAllRecipes();

        return recipeList;
    }

    void loadRecipes() {
        // recipes.clear();

        if (recipes.isEmpty())
        {
            recipes.addAll(getRecipesData());
        }

        int recipeColumn = 0;
        int recipeRow = 2;

        try
        {
            for (var recipe : recipes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                stockManager.setRecipeAvailabilityColor(recipe, anchorPane);

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
        // mainMostLikedRecipes.clear();

        if (mainMostLikedRecipes.isEmpty())
        {
             mainMostLikedRecipes = recipes.stream()
                    .sorted(Comparator.comparingDouble(Recipe::getRate).reversed())
                    .limit(7)
                    .collect(Collectors.toList());
        }


        int recipeColumn = 0;
        int recipeRow = 2;

        try
        {
            for (Recipe mostLikedRecipe : mainMostLikedRecipes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/mainMostLikedRecipes.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                stockManager.setRecipeAvailabilityColor(mostLikedRecipe, anchorPane);

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
            clickedButton = (Button) event.getSource();
            setButtonID(clickedButton.getId());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("category-page.fxml"));
            Parent mainRoot = loader.load();

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

        var r = database.getIngredientQuantitiesByRecipeId(16);

        for (var i : r)
        {
            System.out.println("Quantities : " + i);
        }

        database.printAllRelations();
    }
}