package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class FilterController implements Initializable {
    @FXML
    private ComboBox<String> addFilterIngredient;
    @FXML
    private TextField maxPriceField;
    @FXML
    private TextField maxTimeField;
    private static String staticMaxTimeField;
    @FXML
    private Button cancelButton1;
    @FXML
    private Button cancelButton2;
    @FXML
    private Button cancelButton3;
    @FXML
    private Label filteredIngredient1;
    @FXML
    private Label filteredIngredient2;
    @FXML
    private Label filteredIngredient3;
    private int number = 1;

    private List<String> filteredIngredients = new ArrayList<>();
    private static List<String> staticFilteredIngredients = new ArrayList<>();

    private Database database = new Database();
    private static Database staticDatabase = new Database();

    private CategoryPageController categoryPageController = new CategoryPageController();

    private List<Recipe> filterRecipes;
    private List<String> allIngredientNames = new ArrayList<>();
    public static List<Ingredient> ingredients = new ArrayList<>();


    private List<Recipe> getRecipesData() {
        List<Recipe> recipeList = database.getAllRecipes();

        return recipeList;
    }

    private List<Ingredient> getIngredientsData() {
        List<Ingredient> ingredientList = database.getAllIngredients();

        return ingredientList;
    }

    private List<String> getAllIngredientNames(){
        ingredients = getIngredientsData();

        for (var ingredient : ingredients){
            allIngredientNames.add(ingredient.getIngredientName());
        }

        return allIngredientNames;
    }

    public void cancelIngredient(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        int number = Character.getNumericValue(buttonId.charAt(buttonId.length() - 1));
        filteredIngredients.remove(number - 1);

        filteredIngredient1.setText(null);
        filteredIngredient2.setText(null);
        filteredIngredient3.setText(null);

        if (filteredIngredients.size() > 0) {
            filteredIngredient1.setText(filteredIngredients.get(0));
        }
        if (filteredIngredients.size() > 1) {
            filteredIngredient2.setText(filteredIngredients.get(1));
        }
        if (filteredIngredients.size() > 2) {
            filteredIngredient3.setText(filteredIngredients.get(2));
        }


        cancelButton1.setVisible(filteredIngredient1.getText() != null && !filteredIngredient1.getText().isEmpty());
        cancelButton2.setVisible(filteredIngredient2.getText() != null && !filteredIngredient2.getText().isEmpty());
        cancelButton3.setVisible(filteredIngredient3.getText() != null && !filteredIngredient3.getText().isEmpty());
    }

    public void addIngredient(){
        if (filteredIngredients.size() <= 3) {
            filteredIngredients.add(addFilterIngredient.getValue());
        }

        if (filteredIngredients.size() > 0) {
            cancelButton1.setVisible(true);
            filteredIngredient1.setText(filteredIngredients.get(0));
        }

        if (filteredIngredients.size() > 1) {
            cancelButton2.setVisible(true);
            filteredIngredient2.setText(filteredIngredients.get(1));
        }

        if (filteredIngredients.size() > 2) {
            cancelButton3.setVisible(true);
            filteredIngredient3.setText(filteredIngredients.get(2));
        }
    }


    private static Recipe maxTimeFiltering(Recipe recipe) {
        if (staticMaxTimeField != null && !staticMaxTimeField.isEmpty()) {
            try {
                int maxTime = Integer.parseInt(staticMaxTimeField);
                if (recipe.getPreparationTime() <= maxTime) {
                    return recipe;
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçerli bir sayı girin.");
            }
        }
        return null;
    }

    private static Recipe ingredientsFiltering(Recipe recipe) {
        List<String> recipeIngredients = staticDatabase.getIngredientNameListByRecipeId(recipe.getID());
        System.out.println(recipeIngredients);

        for (int i=0; i < staticFilteredIngredients.size(); i++) {
            if (!recipeIngredients.contains(staticFilteredIngredients.get(i))) {
                return null;
            }
        }
        return recipe;
    }

    public static Recipe filtering(Recipe recipe) {
        Recipe ses = new Recipe(999, "deneme", "deneme", 999, "deneme", 999, 999);
        Recipe ses1 = ses;
        Recipe ses2 = ses;

        if (staticMaxTimeField != null && !staticMaxTimeField.isEmpty()) {
            ses = maxTimeFiltering(recipe);
        }

        if (staticFilteredIngredients.size() != 0) {
            ses1 = ingredientsFiltering(recipe);
        }

        if (ses == recipe && ses1 == recipe) {
            return recipe;
        }
        else if ((ses == recipe && ses1 == null) || (ses1 == recipe && ses == null)) {
            return null;
        }
        else if ((ses == recipe && ses1 == ses2) || (ses1 == recipe && ses == ses2)) {
            return recipe;
        }
        else if (ses != recipe && ses1 != recipe) {
            return null;
        }


        /*if (ses == ses1 && ses1 != ses2 && ses != ses2) {
            System.out.println("ilk eşit");
            return recipe;
        }
        else if (ses == ses1 && ses1 == ses2 && ses == ses2) {
            return null;
        }
        else if ((ses != null && ses1 == ses2) || (ses1 != null && ses == ses2)) {
            System.out.println("ikinci");
            return recipe;
        }
        */
        return null;
    }

    public void categoryPageInitialize() throws IOException {
        staticMaxTimeField = maxTimeField.getText();
        staticFilteredIngredients = filteredIngredients;
        staticDatabase = database;

        categoryPageController.basla(categoryPageController.heyGrid, number);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        maxPriceField.setTextFormatter(new TextFormatter<>(numberFilter));
        maxTimeField.setTextFormatter(new TextFormatter<>(numberFilter));

        ObservableList<String> ingredientNames = FXCollections.observableArrayList(getAllIngredientNames());
        addFilterIngredient.setItems(ingredientNames);

    }
}
