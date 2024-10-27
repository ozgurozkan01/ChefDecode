package com.example.demo1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class EditController
{
    @FXML
    private Label informationText;
    @FXML
    private Label recipeName;
    @FXML
    private TextField quantityText;
    @FXML
    private TextField newRecipeImagePath;
    @FXML
    private TextField timeText;
    @FXML
    private VBox ingredientAdderBox;
    @FXML
    private VBox nameBox;
    @FXML
    private VBox quantityBox;
    @FXML
    private VBox unitBox;
    @FXML
    private VBox instructionsBox;
    @FXML
    private ComboBox<String> ingredientCombo;
    @FXML
    private ComboBox<String> unitTypeCombo;

    private String oldTimerText;
    private String oldImagePath;

    boolean isTimeChanged = false;
    boolean isImageChanged = false;
    boolean isIngredientsChanged = false;
    boolean isInstructionsChanged = false;

    List<String> recipeIngredients;
    String[] recipeInstructions;

    private List<TextField> oldIngredients = new ArrayList<>();
    private List<TextField> oldInstructions = new ArrayList<>();

    private TextField newIngredientField;
    private TextField ingredientStorageQuantity;
    private TextField priceText;


    private Database db = new Database();
    private Recipe recipe;
    private Scene previousScene;
    private Stage stage;

    public void setRecipe(Recipe r)            { recipe = r; }
    public void setPreviousScene(Scene scene)  { this.previousScene = scene; }
    public void setBeforeStage(Stage stage)    { this.stage = stage; }

    boolean isAddingNewIngredient;

    public void OnIngredientComboBoxPressed()
    {
        if (ingredientCombo.getValue().equals("Add New Ingredient..."))
        {
            ingredientAdderBox.getChildren().get(3).setVisible(true);
            ingredientAdderBox.getChildren().get(4).setVisible(true);
            ingredientAdderBox.getChildren().get(5).setVisible(true);
            unitTypeCombo.setVisible(true);
            isAddingNewIngredient = true;
            quantityText.setPromptText("Enter ingredient quantity");
            quantityText.setText("");
            return;
        }

        isAddingNewIngredient = false;
        ingredientAdderBox.getChildren().get(3).setVisible(false);
        ingredientAdderBox.getChildren().get(4).setVisible(false);
        ingredientAdderBox.getChildren().get(5).setVisible(false);
        unitTypeCombo.setVisible(false);
        quantityText.setPromptText("Enter ingredient quantity (" + db.getIngredientUnitTypeByName(ingredientCombo.getValue()) + ")" );
        quantityText.setText("");
    }

    private void checkChangesOnVBox(VBox vbox, List<TextField> oldValuesList, String boxType)
    {
        for (int i = 0; i < vbox.getChildren().size(); i++)
        {
            Node node = vbox.getChildren().get(i);
            if (node instanceof TextField textField)
            {
                final int index = i;
                textField.textProperty().addListener((observable, oldVal, newVal) ->
                {
                    boolean isChanged = !newVal.equals(oldValuesList.get(index).getText());
                    if (isChanged)
                    {
                        informationText.setText("- File Is Changed -");
                        System.out.println(boxType + " box'ta değişiklik algılandı. Eski Değer = " + oldValuesList.get(index) + ", Yeni Değer = " + newVal);
                        if (boxType.equals("ingredients"))
                        {
                            isIngredientsChanged = true;
                        }
                        else if (boxType.equals("instructions"))
                        {
                            isInstructionsChanged = true;
                        }
                    }
                });
            }
        }
    }

    public void loadProperties()
    {
        ObservableList<String> units = FXCollections.observableArrayList("kg", "g", "l", "ml", "adet");
        unitTypeCombo.setItems(units);
        unitTypeCombo.setVisible(false);

        timeText.setText(Integer.toString(recipe.getPreparationTime()));
        recipeName.setText(recipe.getName());

        List<String> quantityList   = db.getIngredientQuantityListByRecipeId(recipe.getID());
        List<String> nameList       = db.getIngredientNameListByRecipeId(recipe.getID());
        List<String> unitList       = db.getUnitListByRecipeId(recipe.getID());

        for (int i = 0; i < nameList.size(); i++)
        {
            nameBox.getChildren().add(new Label(nameList.get(i)));
            nameBox.getChildren().getLast().setStyle("-fx-padding: 7 0 0;");
            quantityBox.getChildren().add(new TextField(quantityList.get(i)));
            unitBox.getChildren().add(new Label(unitList.get(i)));
            unitBox.getChildren().getLast().setStyle("-fx-padding: 7 0 0 0;");
            // System.out.println(ingredient);
            oldIngredients.add(new TextField(quantityList.get(i)));
        }

        recipeInstructions = recipe.getInstruction().split("\\.");

        for (var instruction : recipeInstructions)
        {
            instructionsBox.getChildren().add(new TextField(instruction));
            oldInstructions.add(new TextField(instruction));
        }

        checkChangesOnVBox(quantityBox, oldIngredients, "ingredients");
        checkChangesOnVBox(instructionsBox, oldInstructions, "instructions");

        oldTimerText = timeText.getText();

        timeText.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                isTimeChanged = !timeText.getText().equals(oldTimerText);
                if (isTimeChanged)
                {
                    informationText.setText("- File Is Changed -");
                }
            }
        });

        newRecipeImagePath.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1)
            {
                isImageChanged = !newRecipeImagePath.getText().equals(oldImagePath);

                if (isImageChanged)
                {
                    informationText.setText("- File Is Changed -");
                }
            }
        });

        ingredientStorageQuantity = new TextField();
        ingredientStorageQuantity.setPromptText("Enter Storage Quantity");

        newIngredientField = new TextField();
        newIngredientField.setPromptText("New Ingredient Name");

        priceText = new TextField();
        priceText.setPromptText("Enter Unit Price");

        ingredientAdderBox.getChildren().add(3, newIngredientField);
        ingredientAdderBox.getChildren().add(4, ingredientStorageQuantity);
        ingredientAdderBox.getChildren().add(5, priceText);

        ingredientAdderBox.getChildren().get(3).setVisible(false);
        ingredientAdderBox.getChildren().get(4).setVisible(false);
        ingredientAdderBox.getChildren().get(5).setVisible(false);

        ObservableList<String> ingredientOptions = FXCollections.observableArrayList("Add New Ingredient...");

        List<Ingredient> ingredientsFromDb = db.getAllIngredients();
        for (Ingredient ingredient : ingredientsFromDb)
        {
            ingredientOptions.add(ingredient.getIngredientName());
        }

        ingredientCombo.setItems(ingredientOptions);
    }

    private boolean isThereAnyChangeOnProperties()
    {
        return isImageChanged || isTimeChanged || isIngredientsChanged || isInstructionsChanged;
    }

    public void OnCancelButtonPressed()
    {
        if (previousScene != null && stage != null && !isThereAnyChangeOnProperties())
        {
            stage.setScene(previousScene);
        }
        else
        {
            informationText.setText("- Please Save Changes Before Cancel - ");
        }
    }

    public void OnSaveButtonPressed()
    {
        informationText.setText("- All Changes Are Saved -");
        if (isInstructionsChanged)
        {
            List<String> newInstructions = new ArrayList<>();
            for (Node node : instructionsBox.getChildren()) {
                if (node instanceof TextField textField) {
                    newInstructions.add(textField.getText());
                }
            }
            String updatedInstructions = String.join(".\n", newInstructions);

            db.updateInstructions(recipe.getID(), updatedInstructions);
            recipe.setInstruction(updatedInstructions);
        }
        if (isIngredientsChanged)
        {
            db.updateRecipeIngredients(recipe.getID(), nameBox, quantityBox);
        }
        if (isImageChanged) {}
        if (isTimeChanged) { db.updatePreparationTime(recipe.getID(), Integer.parseInt(timeText.getText())); }

        Recipe updatedRecipe = db.getRecipe(recipe.getID());
        if (updatedRecipe != null)
        {
            recipe.setPreparationTime(updatedRecipe.getPreparationTime());
        }

        if (isThereAnyChangeOnProperties())
        {
            MainController.recipes.set(MainController.recipes.indexOf(recipe), recipe);
        }
    }
    public void OnAddNewIngredientRowPressed()
    {
        String selectedValue = isAddingNewIngredient ? newIngredientField.getText() : ingredientCombo.getValue();
        Ingredient existingIngredient = db.getIngredientByName(selectedValue);

        if (isAddingNewIngredient)
        {
            int newIngredientID = db.getMaxIngredientIdFromDatabase() + 1;
            Ingredient newIngredient = new Ingredient(newIngredientID, selectedValue, quantityText.getText(), unitTypeCombo.getValue(), Integer.parseInt(priceText.getText()));
            db.insertIngredient(newIngredient);
            db.insertRelation(recipe.getID(), newIngredientID, Integer.parseInt(quantityText.getText()));

            System.out.println("Added new ingredient: " + newIngredient.getIngredientName());
        }

        // UI güncellemeleri
        nameBox.getChildren().add(new Label(selectedValue));
        nameBox.getChildren().get(nameBox.getChildren().size() - 1).setStyle("-fx-padding: 7 0 0 0;");
        quantityBox.getChildren().add(new TextField(quantityText.getText()));
        unitBox.getChildren().add(new Label(db.getIngredientUnitTypeByName(ingredientCombo.getValue())));
        unitBox.getChildren().get(unitBox.getChildren().size() - 1).setStyle("-fx-padding: 7 0 0 0;");

    }
    public void OnDeleteLastIngredientRowPressed()
    {
        informationText.setText("- File Is Changed -");
        nameBox.getChildren().remove(nameBox.getChildren().getLast());
        quantityBox.getChildren().remove(quantityBox.getChildren().getLast());
        unitBox.getChildren().remove(unitBox.getChildren().getLast());
        isIngredientsChanged = true;
    }
    public void OnAddNewInstructionRowPressed()
    {
        informationText.setText("- File Is Changed -");
        TextField newTextField = new TextField("");
        newTextField.setPromptText("Add New Instruction");
        instructionsBox.getChildren().add(newTextField);
        isInstructionsChanged = true;
    }
    public void OnDeleteLastInstructionRowPressed()
    {
        informationText.setText("- File Is Changed -");
        instructionsBox.getChildren().remove(instructionsBox.getChildren().getLast());
        isInstructionsChanged = true;
    }

    public void OnDeleteButtonPressed()
    {
        if (previousScene != null && stage != null)
        {
            MainController.recipes.remove(recipe);
            db.deleteRecipe(recipe.getID());

            try
            {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));

                Scene scene = new Scene(fxmlLoader.load());

                stage.setTitle("Chef Decode");
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e){}
        }
    }
}
