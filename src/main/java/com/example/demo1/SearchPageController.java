package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPageController implements Initializable {
    @FXML
    private GridPane grid;

    private Database database = new Database();
    private StockManager stockManager = new StockManager();
    static String searchText;

    public void searchStart(String searchBarText) {
        searchText = searchBarText;

        int column = 0;
        int row = 2;

        try {
            for (Recipe recipe : MainController.recipes) {
                List<String> ss = database.searchRecipe(recipe.getID());

                if (ss.stream().anyMatch(s -> s.equalsIgnoreCase(searchBarText))) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/recipe.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    stockManager.setRecipeAvailabilityColor(recipe, anchorPane);

                    RecipeController recipeController = fxmlLoader.getController();
                    recipeController.setData(recipe);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (grid == null) {
            System.out.println("GridPane nesnesi null. Lütfen kontrol edin.");
        }
        searchStart(null);
    }
}
