package com.example.demo1;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryPageController implements Initializable {
    @FXML
    private GridPane grid;
    static GridPane heyGrid;

    @FXML
    private ScrollPane scroll;

    private Database database = new Database();

    private String buttonID;
    private int number = 0;


    public void basla(GridPane grid, int number) throws IOException {
        buttonID = MainController.getButtonID();

        int column = 0;
        int row = 2;

        if (number == 1) {
            grid.getChildren().clear();

            try {
                for (Recipe recipe : MainController.recipes) {
                    if (recipe.getCategory().equals(buttonID) && FilterController.filtering(recipe) != null) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                        AnchorPane anchorPane = fxmlLoader.load();

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
        else {
            try {
                for (Recipe recipe : MainController.recipes) {
                    if (recipe.getCategory().equals(buttonID)) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                        AnchorPane anchorPane = fxmlLoader.load();

                        RecipeController recipeController = fxmlLoader.getController();
                        recipeController.setData(recipe);

                        if (column == 3) {
                            column = 0;
                            row++;
                        }

                        grid.add(anchorPane, column++, row);
                        heyGrid = grid;

                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(grid.getChildren().size());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            basla(grid, number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
