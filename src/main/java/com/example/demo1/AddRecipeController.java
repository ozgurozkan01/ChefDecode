package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable
{
    Database db = new Database();

    @FXML
    private TextField nameInput;
    @FXML
    private TextField timeInput;
    @FXML
    private Button deleteRowButton;
    @FXML
    private Button addRowButton;
    @FXML
    private ListView<String> addedIngredientList;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteLastIngredient;
    @FXML
    private Button addIngredient;
    @FXML
    private HBox includeHeader;
    @FXML
    private ComboBox<String> ingredientCombo;
    @FXML
    private ComboBox<String> unitTypeCombo;
    @FXML
    private ComboBox<String> categoryCombo;
    @FXML
    private TextField quantityText;
    @FXML
    private VBox ingredientBox;
    @FXML
    private VBox recipeBox;
    @FXML
    private TextField ingredientQuantity;

    ObservableList<String> ingredientNames = FXCollections.observableArrayList();

    private TextField newIngredientField;
    private TextField ingredientStorageQuantity;
    private TextField priceText;

    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<TextField> instructionTextFieldList = new ArrayList<>();

    int rowIndex = 5;

    boolean isAddingNewIngredient;

    public void OnIngredientComboBoxPressed()
    {
        if (ingredientCombo.getValue().equals("Add New Ingredient..."))
        {
            ingredientBox.getChildren().get(3).setVisible(true);
            ingredientBox.getChildren().get(4).setVisible(true);
            ingredientBox.getChildren().get(5).setVisible(true);
            unitTypeCombo.setVisible(true);
            isAddingNewIngredient = true;
            ingredientQuantity.setPromptText("Enter ingredient quantity");
            return;
        }

        isAddingNewIngredient = false;
        ingredientBox.getChildren().get(3).setVisible(false);
        ingredientBox.getChildren().get(4).setVisible(false);
        ingredientBox.getChildren().get(5).setVisible(false);
        unitTypeCombo.setVisible(false);
        ingredientQuantity.setPromptText("Enter ingredient quantity (" + db.getIngredientUnitTypeByName(ingredientCombo.getValue()) + ")" );
    }

    public void OnAddIngredientButtonPressed()
    {
        String ingredientName = isAddingNewIngredient ? newIngredientField.getText() : ingredientCombo.getValue();
        String quantityType = isAddingNewIngredient ? unitTypeCombo.getValue() : db.getIngredientUnitTypeByName(ingredientName);
        String quantity = isAddingNewIngredient ? ingredientStorageQuantity.getText() : ingredientQuantity.getText();
        String unitPrice = isAddingNewIngredient ? priceText.getText() : db.getIngredientUnitPrice(ingredientName);

        if (!ingredientName.isEmpty() && !quantityType.isEmpty() && !quantity.isEmpty() && !unitPrice.isEmpty()) {
            int ingredientID;
            Ingredient existingIngredient = db.getIngredientByName(ingredientName);

            if (existingIngredient != null) { ingredientID = existingIngredient.getIngredientID(); }
            else                            { ingredientID = db.getMaxIngredientIdFromDatabase() + 1; }

            Ingredient newIngredient = new Ingredient(ingredientID, ingredientName, quantity, quantityType, Integer.parseInt(unitPrice));

            if (!ingredientNames.contains(newIngredient.getIngredientName())) {
                ingredientNames.add(ingredientName);
                addedIngredientList.setItems(ingredientNames);
                ingredientList.add(newIngredient);

                if (isAddingNewIngredient && existingIngredient != null) {
                    int updatedQuantity = Integer.parseInt(existingIngredient.getQuantity()) + Integer.parseInt(quantity);
                    existingIngredient.setQuantity(String.valueOf(updatedQuantity));
                    System.out.println("Updated storage for " + existingIngredient.getIngredientName() + ": " + existingIngredient.getQuantity());
                    db.updateIngredient(existingIngredient);
                } else if (isAddingNewIngredient) {
                    db.insertIngredient(newIngredient);
                }
            }
        }

        System.out.println(ingredientList.size());
    }

    public void OnDeleteIngredientButtonPressed()
    {
        if (!addedIngredientList.getItems().isEmpty())
        {
            addedIngredientList.getItems().remove(addedIngredientList.getItems().getLast());
            ingredientList.remove(ingredientList.getLast());
        }
    }

    public void OnAddRecipeButtonPressed()
    {
        String name = nameInput.getText();
        if (name.isEmpty())
        {
            nameInput.setPromptText("REQUIRED AREA");
            nameInput.setStyle("-fx-prompt-text-fill: #FF0000");
        }
        String category = categoryCombo.getValue();
        if (category.isEmpty())
        {
            categoryCombo.setPromptText("REQUIRED AREA");
            categoryCombo.setStyle("-fx-prompt-text-fill: #FF0000");
        }

        String instruction = "";

        for (var inst : instructionTextFieldList)
        {
            instruction += inst.getText() + "\n";
        }

        String preparationTime = timeInput.getText();
        if (preparationTime.isEmpty())
        {
            timeInput.setPromptText("REQUIRED AREA");
            timeInput.setStyle("-fx-prompt-text-fill: #FF0000");
        }


        if (!name.isEmpty() && !category.isEmpty() && !instruction.isEmpty() && !preparationTime.isEmpty() && !ingredientList.isEmpty())
        {
            Recipe recipe = new Recipe(db.getMaxRecipeIdFromDatabase() + 1, name, category, Integer.parseInt(preparationTime), instruction, 0, 0);
            db.insertRecipe(recipe);
            MainController.recipes.add(recipe);

            System.out.println(ingredientList.size());

            for (var ingredient : ingredientList)
            {
                // db.insertIngredient(ingredient);
                System.out.println(ingredient.getQuantity());
                db.insertRelation(recipe.getID(), ingredient.getIngredientID(), Float.parseFloat(ingredient.getQuantity()));
            }
        }

    }

    public void OnAddNewRowButtonPressed()
    {
        TextField newInstructionRow = new TextField();
        newInstructionRow.setPromptText("Add new instruction");
        instructionTextFieldList.add(newInstructionRow);

        recipeBox.getChildren().add(rowIndex++, newInstructionRow);
    }

    public void OnDeleteLastRowButtonPressed()
    {
        if (!instructionTextFieldList.isEmpty())
        {
            recipeBox.getChildren().remove(rowIndex--);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ingredientStorageQuantity = new TextField();
        ingredientStorageQuantity.setPromptText("Enter Storage Quantity");

        newIngredientField = new TextField();
        newIngredientField.setPromptText("New Ingredient Name");

        priceText = new TextField();
        priceText.setPromptText("Enter Unit Price");

        ingredientBox.getChildren().add(3, newIngredientField);
        ingredientBox.getChildren().add(4, ingredientStorageQuantity);
        ingredientBox.getChildren().add(5, priceText);

        ingredientBox.getChildren().get(3).setVisible(false);
        ingredientBox.getChildren().get(4).setVisible(false);
        ingredientBox.getChildren().get(5).setVisible(false);

        ObservableList<String> ingredientOptions = FXCollections.observableArrayList("Add New Ingredient...");

        List<Ingredient> ingredientsFromDb = db.getAllIngredients();
        for (Ingredient ingredient : ingredientsFromDb)
        {
            ingredientOptions.add(ingredient.getIngredientName());
        }

        ingredientCombo.setItems(ingredientOptions);

        ObservableList<String> categoryList = FXCollections.observableArrayList("mainCourse", "soup", "appetizer", "snack", "dessert");
        categoryCombo.setItems(categoryList);


        ObservableList<String> unitList = FXCollections.observableArrayList("kg", "g", "l", "ml", "adet");
        unitTypeCombo.setItems(unitList);
        unitTypeCombo.setVisible(false);
    }
}
